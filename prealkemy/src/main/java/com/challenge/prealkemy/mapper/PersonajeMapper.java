
package com.challenge.prealkemy.mapper;

import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PersonajeDTO;
import com.challenge.prealkemy.dto.PersonajeDTOBasic;
import com.challenge.prealkemy.entity.PeliculaEntity;
import com.challenge.prealkemy.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author river
 */

@Component

public class PersonajeMapper {
    
    @Autowired
    private PeliculaMapper peliculaMapper;
    
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO personajeDTO) {

        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntityRefreshValues(personajeEntity, personajeDTO);

        return personajeEntity;
    }

    
    public PersonajeDTO personajeEntity2DTO(PersonajeEntity personajeEntity, boolean loadPelicula) {

        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTORefreshValues(personajeDTO, personajeEntity);

        
        if (loadPelicula) {
            List<PeliculaDTO> peliculaDTOList = new ArrayList<>();
            for (PeliculaEntity entity : personajeEntity.getPeliculas()) {
                peliculaDTOList.add(peliculaMapper.peliculaEntity2DTO(entity, false));
            }
            personajeDTO.setPeliculas(peliculaDTOList);
        }
        return personajeDTO;
    }

    
    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entities, boolean loadPelicula) {

        List<PersonajeDTO> personajeDTOList = new ArrayList<>();

        for (PersonajeEntity entity : entities) {
            personajeDTOList.add(personajeEntity2DTO(entity, loadPelicula));
        }
        return personajeDTOList;
    }

    public List<PersonajeDTOBasic> entityList2DTOBasicList(List<PersonajeEntity> entities) {

        List<PersonajeDTOBasic> personajeDTOBasicList = new ArrayList<>();

        for (PersonajeEntity entity : entities) {
            PersonajeDTOBasic personajeDTOBasic = new PersonajeDTOBasic();
            personajeDTOBasicRefreshValue(personajeDTOBasic, entity);

            personajeDTOBasicList.add(personajeDTOBasic);
        }
        return personajeDTOBasicList;
    }

    
    public PersonajeDTOBasic personajeEntity2BasicDTO(PersonajeEntity personajeEntity) {

        PersonajeDTOBasic personajeDTOBasic = new PersonajeDTOBasic();
        personajeDTOBasicRefreshValue(personajeDTOBasic, personajeEntity);
        

        return personajeDTOBasic;
    }


    public void personajeEntityRefreshValues(PersonajeEntity personajeEntity, PersonajeDTO personajeDTO) {

        personajeEntity.setImagen(personajeDTO.getImagen());
        personajeEntity.setNombre(personajeDTO.getNombre());
        personajeEntity.setEdad(personajeDTO.getEdad());
        personajeEntity.setPeso(personajeDTO.getPeso());
        personajeEntity.setHistoria(personajeDTO.getHistoria());
    }

    
    public void personajeDTORefreshValues(PersonajeDTO personajeDTO, PersonajeEntity personajeEntity) {

        personajeDTO.setId(personajeEntity.getId());
        personajeDTO.setNombre(personajeEntity.getNombre());
        personajeDTO.setImagen(personajeEntity.getImagen());
        personajeDTO.setEdad(personajeEntity.getEdad());
        personajeDTO.setPeso(personajeEntity.getPeso());
        personajeDTO.setHistoria(personajeEntity.getHistoria());
    }

    
    public void personajeDTOBasicRefreshValue(PersonajeDTOBasic personajeDTOBasic, PersonajeEntity personajeEntity) {

        personajeDTOBasic.setId(personajeEntity.getId());
        personajeDTOBasic.setNombre(personajeEntity.getNombre());
        personajeDTOBasic.setImagen(personajeEntity.getImagen());
    }

    List<PersonajeEntity> personajeDTOList2EntityList(List<PersonajeDTO> personajes) {
        
         List<PersonajeEntity> personajeEntityList = new ArrayList<>();

        for (PersonajeDTO personajeDTO : personajes) {
            personajeEntityList.add(personajeDTO2Entity(personajeDTO));
        }
        return personajeEntityList;
    }
}


