package com.challenge.prealkemy.service.impl;

import com.challenge.prealkemy.Specification.PeliculaSpecification;
import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PeliculaDTOBasic;
import com.challenge.prealkemy.dto.PeliculaDTOFilter;
import com.challenge.prealkemy.entity.GeneroEntity;
import com.challenge.prealkemy.entity.PeliculaEntity;
import com.challenge.prealkemy.entity.PersonajeEntity;
import com.challenge.prealkemy.exception.GeneroException;
import com.challenge.prealkemy.exception.PeliculaException;
import com.challenge.prealkemy.exception.PersonajeException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionMensaje;
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
        try {

            PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(peliculaDTO, false);
            PeliculaEntity savedPelicula = peliculaRepository.save(peliculaEntity);
            PeliculaDTO savedPeliculaDTO = peliculaMapper.peliculaEntity2DTO(savedPelicula, false);

            return savedPeliculaDTO;
        } catch (PeliculaException exception) {
            throw new PeliculaException(ExceptionMensaje.DTO_No_Valido);
        }
    }

    @Override
    public PeliculaDTO modifyPelicula(String idPelicula, PeliculaDTO peliculaDTO) {

        try {

            PeliculaEntity PeliculaGuardada = peliculaRepository.getById(idPelicula);

            peliculaMapper.peliculaEntityRefreshValues(PeliculaGuardada, peliculaDTO);

            PeliculaEntity peliculaModificada = peliculaRepository.save(PeliculaGuardada);

            PeliculaDTO peliculaDTOModificada = peliculaMapper.peliculaEntity2DTO(peliculaModificada, false);

            return peliculaDTOModificada;

        } catch (PeliculaException exception) {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);
        } catch (Exception exception) {
            throw new PersonajeException(ExceptionMensaje.DTO_No_Valido);

        }
    }

    @Override
    public void deletePelicula(String idPelicula) {

        if (peliculaRepository.existsById(idPelicula)) {
            peliculaRepository.deleteById(idPelicula);
        } else {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);
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
    public List<PeliculaDTO> getPeliculaByFilters(String titulo, List<String> generos, String order) {

        PeliculaDTOFilter peliculaDTOFilters = new PeliculaDTOFilter(titulo, generos, order);
        List<PeliculaEntity> peliculaEntityList = peliculaRepository.findAll(peliculaSpecification.getFiltered(peliculaDTOFilters));
        List<PeliculaDTO> peliculaDTOList = peliculaMapper.peliculaEntityList2DTOList(peliculaEntityList, true);

        return peliculaDTOList;
    }

    @Override
    public PeliculaDTO getPeliculaById(String idPelicula) {

        if (peliculaRepository.existsById(idPelicula)) {
            PeliculaEntity peliculaEntity = peliculaRepository.getById(idPelicula);
            PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(peliculaEntity, true);
            return peliculaDTO;
        } else {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);
        }
    }

    @Override
    public void addPersonajeDePelicula(String idPersonaje, String idPelicula) {

        try {
            PeliculaEntity peliculaEntity = peliculaRepository.getById(idPelicula);
            PersonajeEntity personajeEntity = personajeRepository.getById(idPersonaje);

            List<PersonajeEntity> personajes = peliculaEntity.getPersonajes();

            personajes.add(personajeEntity);
            peliculaEntity.setPersonajes(personajes);
            peliculaRepository.save(peliculaEntity);

        } catch (PeliculaException exception) {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);

        } catch (PersonajeException exception) {
            throw new PersonajeException(ExceptionMensaje.PERSONAJE_NO_ENCONTRADO);
        }
    }

    // REMOVE CHARACTER FROM A MOVIE
    @Override
    public void removePersonajeDePelicula(String idPersonaje, String idPelicula) {

         try {

            PeliculaEntity peliculaEntity = peliculaRepository.getById(idPelicula);
            PersonajeEntity personajeEntity = personajeRepository.getById(idPersonaje);

            List<PersonajeEntity> personajes = peliculaEntity.getPersonajes();

            personajes.remove(personajeEntity);
            peliculaEntity.setPersonajes(personajes);
            peliculaRepository.save(peliculaEntity);

        } catch (PeliculaException exception) {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);

        } catch (PersonajeException exc) {
            throw new PersonajeException(ExceptionMensaje.PERSONAJE_NO_ENCONTRADO);
        }

    }
    

    @Override
    public void addGeneroDePelicula(String idGenero, String idPelicula) {
 try {

            PeliculaEntity peliculaEntity = peliculaRepository.getById(idPelicula);
            GeneroEntity generoEntity = generoRepository.getById(idGenero);

            List<GeneroEntity> generos = peliculaEntity.getGeneros();

            generos.add(generoEntity);
            peliculaEntity.setGeneros(generos);
            peliculaRepository.save(peliculaEntity);

        } catch (PeliculaException exception) {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);

        } catch (GeneroException exception) {
            throw new GeneroException(ExceptionMensaje.GENERO_NO_ENCONTRADO);
        }

    }

      
    @Override
    public void removeGeneroDePelicula(String idGenero, String idPelicula) {

        try {

            PeliculaEntity peliculaEntity = peliculaRepository.getById(idPelicula);
            GeneroEntity genero = generoRepository.getById(idGenero);

            List<GeneroEntity> generos = peliculaEntity.getGeneros();

            generos.remove(genero);
            peliculaEntity.setGeneros(generos);
            peliculaRepository.save(peliculaEntity);

        } catch (PeliculaException exc) {
            throw new PeliculaException(ExceptionMensaje.PELICULA_NO_ENCONTRADA);

        } catch (GeneroException exc) {
            throw new GeneroException(ExceptionMensaje.GENERO_NO_ENCONTRADO);
        }

    }
}
