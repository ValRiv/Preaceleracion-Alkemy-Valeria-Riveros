package com.challenge.prealkemy.service.impl;

import com.challenge.prealkemy.Specification.PeliculaSpecification;
import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PeliculaDTOBasic;
import com.challenge.prealkemy.dto.PeliculaDTOFilter;
import com.challenge.prealkemy.entity.GeneroEntity;
import com.challenge.prealkemy.entity.PeliculaEntity;
import com.challenge.prealkemy.entity.PersonajeEntity;
import com.challenge.prealkemy.exception.PeliculaException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionsMensaje;
import com.challenge.prealkemy.mapper.PeliculaMapper;
import com.challenge.prealkemy.repository.GeneroRepository;
import com.challenge.prealkemy.repository.PeliculaRepository;
import com.challenge.prealkemy.repository.PersonajeRepository;
import com.challenge.prealkemy.service.PeliculaService;
import com.challenge.prealkemy.validaciones.ValidacionesDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Lazy
@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Lazy
    @Autowired
    private PeliculaSpecification peliculaSpecification;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ValidacionesDTO validacionesDTO;

    @Override
    public PeliculaDTO savePelicula(PeliculaDTO peliculaDTO) {

        
            PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(peliculaDTO, false);
            PeliculaEntity savedPelicula = peliculaRepository.save(peliculaEntity);
            PeliculaDTO savedPeliculaDTO = peliculaMapper.peliculaEntity2DTO(savedPelicula, false);

            return savedPeliculaDTO;
        
    }

    @Override
    public PeliculaDTO modifyPelicula(String idPelicula, PeliculaDTO peliculaDTO) {

        if (peliculaRepository.existsById(idPelicula)) {
            if (validacionesDTO.peliculaDTOValido(peliculaDTO)) {

                PeliculaEntity savedPelicula = peliculaRepository.getById(idPelicula);

                savedPelicula.setImagen(peliculaDTO.getImagen());
                savedPelicula.setTitulo(peliculaDTO.getTitulo());

                String date = peliculaDTO.getFechaDeCreacion();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate transformedDate = LocalDate.parse(date, formatter);

                savedPelicula.setFechaDeCreacion(transformedDate);

                savedPelicula.setCalificacion(peliculaDTO.getCalificacion());

                PeliculaEntity modifiedPeliculaEntity = peliculaRepository.save(savedPelicula);

                PeliculaDTO modifiedPeliculaDTO = peliculaMapper.peliculaEntity2DTO(modifiedPeliculaEntity, false);

                return modifiedPeliculaDTO;

            } else {
                throw new PeliculaException(ExceptionsMensaje.DTO_WRONG_DATA);
            }
        } else {
            throw new PeliculaException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);
        }
    }

    @Override
    public void deletePelicula(String idPelicula) {

        if (peliculaRepository.existsById(idPelicula)) {
            peliculaRepository.deleteById(idPelicula);
        } else {
            throw new PeliculaException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);
        }
    }

    @Override
    public List<PeliculaDTOBasic> getAllPeliculaBasic() {

        List<PeliculaEntity> peliculaEntityList = peliculaRepository.findAll();
        List<PeliculaDTOBasic> peliculaDTOBasicList = peliculaMapper.peliculaEntityList2BasicDTO(peliculaEntityList);

        return peliculaDTOBasicList;
    }

    @Override
    public List<PeliculaDTO> getAllPeliculas() {

        List<PeliculaEntity> peliculaEntityList = peliculaRepository.findAll();
        List<PeliculaDTO> peliculaDTOList = peliculaMapper.peliculaEntityList2DTOList(peliculaEntityList, true);

        return peliculaDTOList;
    }

    @Override
    public List<PeliculaDTO> getPeliculaByFilters(String titulo, List<String> personajes, List<String> genero, String order) {

        if (titulo.isEmpty() || titulo == null && personajes.isEmpty() || personajes == null
                && genero.isEmpty() || genero == null && order.isEmpty() || order == null) {

            return getAllPeliculas();

        } else {

            PeliculaDTOFilter peliculaDTOFilters = new PeliculaDTOFilter(titulo, personajes, genero, order);
            List<PeliculaEntity> peliculaEntityList = peliculaRepository.findAll(peliculaSpecification.getFiltered(peliculaDTOFilters));
            List<PeliculaDTO> peliculaDTOList = peliculaMapper.peliculaEntityList2DTOList(peliculaEntityList, true);

            return peliculaDTOList;
        }
    }

    @Override
    public PeliculaDTO getPeliculaById(String idPelicula) {

        if (peliculaRepository.existsById(idPelicula)) {
            PeliculaEntity peliculaEntity = peliculaRepository.getById(idPelicula);
            PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(peliculaEntity, true);
            return peliculaDTO;
        } else {
            throw new PeliculaException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);
        }
    }

    @Override
    public void addPersonajeDePelicula(String idPersonaje, String idPelicula) {

        if (personajeRepository.existsById(idPersonaje) && peliculaRepository.existsById(idPelicula)) {
            PeliculaEntity pelicula = peliculaRepository.getById(idPelicula);
            PersonajeEntity personaje = personajeRepository.getById(idPersonaje);
            List<PersonajeEntity> personajes = pelicula.getPersonajes();
            personajes.add(personaje);
            pelicula.setPersonajes(personajes);
            peliculaRepository.save(pelicula);

        } else if (!personajeRepository.existsById(idPersonaje)) {
            throw new EntityNotFoundException(ExceptionsMensaje.PERSONAJE_NO_ENCONTRADO);

        } else if (!peliculaRepository.existsById(idPelicula)) {
            throw new EntityNotFoundException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);
        }
    }

    // REMOVE CHARACTER FROM A MOVIE
    @Override
    public void removePersonajeDePelicula(String idPersonaje, String idPelicula) {

        if (personajeRepository.existsById(idPersonaje) && peliculaRepository.existsById(idPelicula)) {
            PeliculaEntity pelicula = peliculaRepository.getById(idPelicula);
            PersonajeEntity character = personajeRepository.getById(idPersonaje);
            List<PersonajeEntity> personajes = pelicula.getPersonajes();
            personajes.remove(personajes);
            pelicula.setPersonajes(personajes);
            peliculaRepository.save(pelicula);

        } else if (!personajeRepository.existsById(idPersonaje)) {
            throw new EntityNotFoundException(ExceptionsMensaje.PERSONAJE_NO_ENCONTRADO);

        } else if (!peliculaRepository.existsById(idPelicula)) {
            throw new EntityNotFoundException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);
        }
    }

    @Override
    public void addGeneroDePelicula(String idGenero, String idPelicula) {

        if (generoRepository.existsById(idGenero) && peliculaRepository.existsById(idPelicula)) {
            PeliculaEntity pelicula = peliculaRepository.getById(idPelicula);
            GeneroEntity genero = generoRepository.getById(idGenero);
            List<GeneroEntity> generos = pelicula.getGeneros();
            generos.add(genero);
            pelicula.setGeneros(generos);
            peliculaRepository.save(pelicula);

        } else if (!generoRepository.existsById(idGenero)) {
            throw new EntityNotFoundException(ExceptionsMensaje.GENERO_NO_ENCONTRADO);

        } else if (!peliculaRepository.existsById(idPelicula)) {
            throw new EntityNotFoundException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);

        }
    }

    @Override
    public void removeGeneroDePelicula(String idGenero, String idPelicula) {

        if (generoRepository.existsById(idGenero) && peliculaRepository.existsById(idPelicula)) {
            PeliculaEntity pelicula = peliculaRepository.getById(idPelicula);
            GeneroEntity genero = generoRepository.getById(idGenero);
            List<GeneroEntity> generos = pelicula.getGeneros();
            generos.remove(genero);
            pelicula.setGeneros(generos);
            peliculaRepository.save(pelicula);

        } else if (!generoRepository.existsById(idGenero)) {
            throw new EntityNotFoundException(ExceptionsMensaje.GENERO_NO_ENCONTRADO);

        } else if (!peliculaRepository.existsById(idPelicula)) {
            throw new EntityNotFoundException(ExceptionsMensaje.PELICULA_NO_ENCONTRADA);
        }
    }
}
