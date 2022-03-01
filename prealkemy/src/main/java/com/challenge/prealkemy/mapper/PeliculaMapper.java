package com.challenge.prealkemy.mapper;

import com.challenge.prealkemy.dto.GeneroDTO;
import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PeliculaDTOBasic;
import com.challenge.prealkemy.dto.PersonajeDTO;
import com.challenge.prealkemy.entity.GeneroEntity;
import com.challenge.prealkemy.entity.PeliculaEntity;
import com.challenge.prealkemy.entity.PersonajeEntity;
import com.challenge.prealkemy.exception.PeliculaException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionsMensaje;
import com.challenge.prealkemy.validaciones.ValidacionesDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 *
 * @author river
 */

@Component
public class PeliculaMapper {

    @Lazy
     @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private GeneroMapper generoMapper;
    
     @Autowired
    private ValidacionesDTO validacionesDTO;
    

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO peliculaDTO, boolean loadPersonaje) {

            PeliculaEntity peliculaEntity = new PeliculaEntity();
            peliculaEntityRefreshValues(peliculaEntity, peliculaDTO);
            return peliculaEntity;

            }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity peliculaEntity, boolean loadPersonaje) {

        if (peliculaEntity != null) {
            PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTORefreshValues(peliculaDTO, peliculaEntity, loadPersonaje);
            return peliculaDTO;

        } else {
            throw new PeliculaException(ExceptionsMensaje.ENTITY_WRONG_DATA);
        }
    }

    public PeliculaDTOBasic peliculaEntity2BasicDTO(PeliculaEntity peliculaEntity) {

        if (peliculaEntity != null) {
            PeliculaDTOBasic peliculaDTOBasic = new PeliculaDTOBasic();
            peliculaDTOBasicRefreshValues(peliculaDTOBasic, peliculaEntity);
            return peliculaDTOBasic;

        } else {
            throw new PeliculaException(ExceptionsMensaje.ENTITY_WRONG_DATA);
        }
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity> entities, boolean load) {

        List<PeliculaDTO> peliculaDTOList = new ArrayList<>();

        for (PeliculaEntity entity : entities) {
            peliculaDTOList.add(peliculaEntity2DTO(entity, load));
        }
        return peliculaDTOList;
    }

    public List<PeliculaEntity> peliculaDTOList2EntityList(List<PeliculaDTO> peliculaDTOList, boolean loadPersonaje) {

        List<PeliculaEntity> peliculaEntitiesList = new ArrayList<>();

        for (PeliculaDTO dto : peliculaDTOList) {
            peliculaEntitiesList.add(peliculaDTO2Entity(dto, loadPersonaje));
        }
        return peliculaEntitiesList;
    }

    public List<PeliculaDTOBasic> peliculaEntityList2BasicDTO(List<PeliculaEntity> entities) {

        List<PeliculaDTOBasic> peliculaDTOBasicList = new ArrayList<>();

        if (entities != null) {
            for (PeliculaEntity entity : entities) {
                peliculaDTOBasicList.add(peliculaEntity2BasicDTO(entity));
            }
        }
        return peliculaDTOBasicList;
    }

    private LocalDate string2LocalDate(String stringDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate formattedDate = LocalDate.parse(stringDate, formatter);

        return formattedDate;
    }

    private String localDate2String(LocalDate localDate) {

        String formatDate = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        return formatDate;
    }

    public void peliculaEntityRefreshValues(PeliculaEntity peliculaEntity, PeliculaDTO peliculaDTO) {

        peliculaEntity.setImagen(peliculaDTO.getImagen());
        peliculaEntity.setTitulo(peliculaDTO.getTitulo());

        String date = peliculaDTO.getFechaDeCreacion();
        peliculaEntity.setFechaDeCreacion(string2LocalDate(date));

        peliculaEntity.setCalificacion(peliculaDTO.getCalificacion());

        List<PersonajeEntity> personajeEntityList = personajeMapper.personajeDTOList2EntityList(peliculaDTO.getPersonajes());
        peliculaEntity.setPersonajes(personajeEntityList);

        List<GeneroEntity> generoEntityList = generoMapper.generoDTOList2EntityList(peliculaDTO.getGeneros());
        peliculaEntity.setGeneros(generoEntityList);

    }

    public void peliculaDTORefreshValues(PeliculaDTO peliculaDTO, PeliculaEntity peliculaEntity, boolean loadPersonaje) {

        peliculaDTO.setId(peliculaEntity.getId());
        peliculaDTO.setImagen(peliculaEntity.getImagen());
        peliculaDTO.setTitulo(peliculaEntity.getTitulo());

        LocalDate date = peliculaEntity.getFechaDeCreacion();
        peliculaDTO.setFechaDeCreacion(localDate2String(date));

        peliculaDTO.setCalificacion(peliculaEntity.getCalificacion());

        if (loadPersonaje) {

            peliculaDTO.setPersonajes((List<PersonajeDTO>) personajeMapper.personajeEntityList2DTOList((List<PersonajeEntity>) peliculaEntity.getPersonajes(), true));

            peliculaDTO.setGeneros((List<GeneroDTO>) generoMapper.generoEntityList2DTOList((List<GeneroEntity>) peliculaEntity.getGeneros()));
        }

    }

    public void peliculaDTOBasicRefreshValues(PeliculaDTOBasic peliculaDTOBasic, PeliculaEntity peliculaEntity) {
        
        peliculaDTOBasic.setId(peliculaEntity.getId());
        peliculaDTOBasic.setImagen(peliculaEntity.getImagen());
        peliculaDTOBasic.setTitulo(peliculaEntity.getTitulo());

        LocalDate localDate = peliculaEntity.getFechaDeCreacion();
        peliculaDTOBasic.setFechaDeCreacion(localDate2String(localDate));
    }
}
