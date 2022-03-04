package com.challenge.prealkemy.service.impl;

import com.challenge.prealkemy.Specification.PersonajeSpecification;
import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PersonajeDTO;
import com.challenge.prealkemy.dto.PersonajeDTOBasic;
import com.challenge.prealkemy.dto.PersonajeDTOFilter;
import com.challenge.prealkemy.entity.PersonajeEntity;
import com.challenge.prealkemy.exception.PersonajeException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionMensaje;
import com.challenge.prealkemy.mapper.PersonajeMapper;
import com.challenge.prealkemy.repository.PersonajeRepository;
import com.challenge.prealkemy.service.PersonajeService;
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

    
    @Override
    public PersonajeDTO savePersonaje(PersonajeDTO personajeDTO) {

        try{

            PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(personajeDTO);

            PersonajeEntity savedPersonaje = personajeRepository.save(personajeEntity);

            PersonajeDTO savedPersonajeDTO = personajeMapper.personajeEntity2DTO(savedPersonaje, false);

            return savedPersonajeDTO;

        } catch (PersonajeException exception) {
            
            throw new PersonajeException(ExceptionMensaje.DTO_No_Valido);
        }

    }

    @Override
    public PersonajeDTO modifyPersonaje(String idPersonaje, PersonajeDTO personajeDTO) {
        
try{
    
           PersonajeEntity personajeEntity = personajeRepository.getById(idPersonaje);
            personajeEntity.setNombre(personajeDTO.getNombre());
            personajeEntity.setImagen(personajeDTO.getImagen());
            PersonajeEntity personajeModificado = personajeRepository.save(personajeEntity);
            PersonajeDTO dtoModificado = personajeMapper.personajeEntity2DTO(personajeModificado, false);

            return dtoModificado;

        } catch (PersonajeException exception) {
            throw new PersonajeException(ExceptionMensaje.GENERO_NO_ENCONTRADO);

        } catch (Exception exception) {
            throw new PersonajeException(ExceptionMensaje.DTO_No_Valido);
        }
    }

    @Override
    public List<PersonajeDTOBasic> personajeBasico() {

        List<PersonajeEntity> entities = personajeRepository.findAll();

        List<PersonajeDTOBasic> listPersonajeDTOBasic = personajeMapper.entityList2DTOBasicList(entities);

        return listPersonajeDTOBasic;
    }

    @Override
    public List<PersonajeDTO> personajeCompleto() {

        List<PersonajeEntity> listPersonaje = personajeRepository.findAll();
        List<PersonajeDTO> listPersonajeDTO = personajeMapper.personajeEntityList2DTOList(listPersonaje, true);
    
        return listPersonajeDTO;
    }

    @Override
    public List<PersonajeDTO> getPersonajeByFilters(String nombre,Integer edad, List<String> peliculas, String orden) {
      
        PersonajeDTOFilter personajeFilter = new PersonajeDTOFilter(nombre,  edad, peliculas, orden);

        List<PersonajeEntity> personajeEntity = personajeRepository.findAll(personajeSpecification.getByFilters(personajeFilter));

        List<PersonajeDTO> listPersonajeDTO = personajeMapper.personajeEntityList2DTOList(personajeEntity, true);

        return listPersonajeDTO;
    }


    @Override
    public void deletePersonaje(String idPersonaje) {
        if (personajeRepository.existsById(idPersonaje)) {
            personajeRepository.deleteById(idPersonaje);
        } else {
            throw new EntityNotFoundException(ExceptionMensaje.PERSONAJE_NO_ENCONTRADO);
        }
    }

    
    @Override
    public PersonajeDTO getPersonajeById(String idPersonaje) {
        if (personajeRepository.existsById(idPersonaje)) {

            PersonajeEntity personajeEntity = personajeRepository.getById(idPersonaje);
            PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(personajeEntity, true);

            return personajeDTO;

        } else {
            throw new EntityNotFoundException(ExceptionMensaje.PERSONAJE_NO_ENCONTRADO);
        }
    }
  
}
    