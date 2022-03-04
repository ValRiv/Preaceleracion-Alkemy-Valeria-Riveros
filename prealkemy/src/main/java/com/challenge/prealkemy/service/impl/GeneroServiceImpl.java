
package com.challenge.prealkemy.service.impl;

import com.challenge.prealkemy.dto.GeneroDTO;
import com.challenge.prealkemy.dto.GeneroDTOBasic;
import com.challenge.prealkemy.entity.GeneroEntity;
import com.challenge.prealkemy.exception.GeneroException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionMensaje;
import com.challenge.prealkemy.mapper.GeneroMapper;
import com.challenge.prealkemy.repository.GeneroRepository;
import com.challenge.prealkemy.service.GeneroService;
import com.challenge.prealkemy.validaciones.ValidacionesDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Lazy
@Service
public class GeneroServiceImpl implements GeneroService{

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ValidacionesDTO validacionesDTO;

    @Override
    public GeneroDTO saveGenero(GeneroDTO generoDTO) {
            
         if (validacionesDTO.generoDTOValido(generoDTO)) {

            GeneroEntity generoEntity = generoMapper.generoDTO2Entity(generoDTO);
            GeneroEntity generoSaved = generoRepository.save(generoEntity);
            GeneroDTO generoSavedDTO = generoMapper.generoEntity2DTO(generoSaved);

            return generoSavedDTO;

        } else {
            throw new GeneroException(ExceptionMensaje.DTO_No_Valido);
        }
    }
    

    @Override
    public List<GeneroDTO> getAllGeneros() {
     List<GeneroEntity> generoEntities = generoRepository.findAll();
        List<GeneroDTO> generoDTOList = generoMapper.generoEntityList2DTOList(generoEntities);

        return generoDTOList;
    }

    @Override
    public List<GeneroDTOBasic> getAllGenerosBasic() {
         List<GeneroEntity> generoEntities = generoRepository.findAll();
        List<GeneroDTOBasic> generoDTOBasicList = generoMapper.generoEntityList2DTOBasicList(generoEntities);

        return generoDTOBasicList;
    }
    

    @Override
    public GeneroDTO modifyGenero(String id, GeneroDTO generoDTO) {
        
        if (generoRepository.existsById(id)) {
            if (validacionesDTO.generoDTOValido(generoDTO)) {

                GeneroEntity savedGenero = generoRepository.getById(id);
                savedGenero.setNombre(generoDTO.getNombre());
                GeneroEntity modifyGeneroEntity = generoRepository.save(savedGenero);
                GeneroDTO modifyGeneroDTO = generoMapper.generoEntity2DTO(modifyGeneroEntity);

                return modifyGeneroDTO;

            } else {
                throw new GeneroException(ExceptionMensaje.DTO_No_Valido);
            }
        } else {
            throw new GeneroException(ExceptionMensaje.GENERO_NO_ENCONTRADO);
        }
    }
   
    @Override
    public void deleteGeneroById(String id) {
        
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
        } else {
            throw new GeneroException(ExceptionMensaje.GENERO_NO_ENCONTRADO);
        }
    }


    @Override
    public GeneroEntity getGeneroById(String id) {
        Optional<GeneroEntity> generoEntity = generoRepository.findById(id);
        if (!generoEntity.isPresent()) {
            throw new GeneroException(ExceptionMensaje.GENERO_NO_ENCONTRADO);
        }

        return generoEntity.get();
    }
    }
    
            

