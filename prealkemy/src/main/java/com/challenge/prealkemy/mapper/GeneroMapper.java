
package com.challenge.prealkemy.mapper;

import com.challenge.prealkemy.dto.GeneroDTO;
import com.challenge.prealkemy.dto.GeneroDTOBasic;
import com.challenge.prealkemy.entity.GeneroEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author river
 */
@Component
public class GeneroMapper{
    
    public GeneroEntity generoDTO2Entity(GeneroDTO generoDTO) {

        GeneroEntity generoEntity = new GeneroEntity();
        
        
        generoEntity.setNombre(generoDTO.getNombre());
        generoEntity.setImagen(generoDTO.getImagen());

        return generoEntity;
    }

    
    public GeneroDTO generoEntity2DTO(GeneroEntity generoEntity) {

        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(generoEntity.getId());
        generoDTO.setNombre(generoEntity.getNombre());
        generoDTO.setImagen(generoEntity.getImagen());

        return generoDTO;
    }

    
    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entities) {

        List<GeneroDTO> generoDTOList = new ArrayList<>();

        for (GeneroEntity entity : entities) {
            generoDTOList.add(this.generoEntity2DTO(entity));
        }
        return generoDTOList;
    }

    public GeneroDTOBasic generoEntity2BasicDTO(GeneroEntity generoEntity) {

        GeneroDTOBasic generoDTOBasic = new GeneroDTOBasic();
        generoDTOBasic.setId(generoEntity.getId());
        generoDTOBasic.setNombre(generoEntity.getNombre());
        generoDTOBasic.setImagen(generoEntity.getImagen());
        return generoDTOBasic;
    }

    public List<GeneroDTOBasic> generoEntityList2DTOBasicList(List<GeneroEntity> entities) {

        List<GeneroDTOBasic> generoDTOBasicList = new ArrayList<>();

        for (GeneroEntity entity : entities) {
            generoDTOBasicList.add(generoEntity2BasicDTO(entity));
        }
        return generoDTOBasicList;
    }


    List<GeneroEntity> generoDTOList2EntityList(List<GeneroDTO> entities) {

        List<GeneroEntity> generoEntityList = new ArrayList<>();

        for (GeneroDTO generoDTO : entities) {
            generoEntityList.add(generoDTO2Entity(generoDTO));
        }
        return generoEntityList;
    }
}

