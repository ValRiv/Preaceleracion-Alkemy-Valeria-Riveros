package com.challenge.prealkemy.service.impl;

import com.challenge.prealkemy.Specification.PersonajeSpecification;
import com.challenge.prealkemy.dto.PersonajeDTO;
import com.challenge.prealkemy.dto.PersonajeDTOBasic;
import com.challenge.prealkemy.dto.PersonajeDTOFilter;
import com.challenge.prealkemy.entity.PersonajeEntity;
import com.challenge.prealkemy.exception.PersonajeException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionsMensaje;
import com.challenge.prealkemy.mapper.PersonajeMapper;
import com.challenge.prealkemy.repository.PersonajeRepository;
import com.challenge.prealkemy.service.PersonajeService;
import com.challenge.prealkemy.validaciones.ValidacionesDTO;
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
public class PersonajeServiceIml implements PersonajeService {

    @Lazy
    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Lazy
    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Autowired
    private ValidacionesDTO validacionesDTO;

    @Override
    public PersonajeDTO savePersonaje(PersonajeDTO personajeDTO) {

        if (validacionesDTO.personajeDTOValido(personajeDTO)) {

            PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(personajeDTO);

            PersonajeEntity savedPersonaje = personajeRepository.save(personajeEntity);

            PersonajeDTO savedPersonajeDTO = personajeMapper.personajeEntity2DTO(savedPersonaje, false);

            return savedPersonajeDTO;

        } else {
            throw new PersonajeException(ExceptionsMensaje.DTO_WRONG_DATA);
        }

    }

    @Override
    public PersonajeDTO modifyPersonaje(String idPersonaje, PersonajeDTO personajeDTO) {
        if (personajeRepository.existsById(personajeDTO.getId())) {
            if (validacionesDTO.personajeDTOValido(personajeDTO)) {

                PersonajeEntity savedPersonaje = personajeMapper.personajeDTO2Entity(personajeDTO);
                PersonajeEntity editarPersonaje = personajeRepository.save(savedPersonaje);
                PersonajeDTO savedDTO = personajeMapper.personajeEntity2DTO(editarPersonaje, false);

                return savedDTO;

            } else {
                throw new PersonajeException(ExceptionsMensaje.DTO_WRONG_DATA);
            }
        } else {
            throw new PersonajeException(ExceptionsMensaje.PERSONAJE_NO_ENCONTRADO);
        }
    }

    @Override
    public void deletePersonaje(String idPersonaje) {
        if (personajeRepository.existsById(idPersonaje)) {
            personajeRepository.deleteById(idPersonaje);
        } else {
            throw new EntityNotFoundException(ExceptionsMensaje.PERSONAJE_NO_ENCONTRADO);
        }
    }

    @Override
    public List<PersonajeDTOBasic> getAllPersonajeBasic() {
        List<PersonajeEntity> personajeEntityList = personajeRepository.findAll();

        List<PersonajeDTOBasic> personajeDTOBasicList = personajeMapper.entityList2DTOBasicList(personajeEntityList);

        return personajeDTOBasicList;
    }

    @Override
    public List<PersonajeDTO> getAllPersonaje() {
        List<PersonajeEntity> personajeEntities = personajeRepository.findAll();
        List<PersonajeDTO> personajeDTOList = personajeMapper.personajeEntityList2DTOList(personajeEntities, true);

        return personajeDTOList;
    }

    @Override
    public List<PersonajeDTO> getPersonajeByFilters(String nombre, Integer edad, Double peso, List<String> peliculas, String order) {
        if (nombre.isEmpty() || String.valueOf(nombre) == null && String.valueOf(edad).isEmpty() || edad == null
                && String.valueOf(peso).isEmpty() || String.valueOf(peso) == null
                && peliculas.isEmpty() || peliculas == null && order.isEmpty() || order == null) {

            return getAllPersonaje();

        } else {

            PersonajeDTOFilter personajeFilter = new PersonajeDTOFilter(nombre, edad, peso, peliculas, order);

            List<PersonajeEntity> personajeEntities = personajeRepository.findAll(personajeSpecification.getByFilters(personajeFilter));

            List<PersonajeDTO> personajeDTOList = personajeMapper.personajeEntityList2DTOList(personajeEntities, true);

            return personajeDTOList;
        }
    }

    @Override
    public PersonajeDTO getPersonajeById(String idPersonaje) {
        if (personajeRepository.existsById(idPersonaje)) {

            PersonajeEntity personajeEntity = personajeRepository.getById(idPersonaje);
            PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(personajeEntity, true);

            return personajeDTO;

        } else {
            throw new EntityNotFoundException(ExceptionsMensaje.PERSONAJE_NO_ENCONTRADO);
        }
    }

}
