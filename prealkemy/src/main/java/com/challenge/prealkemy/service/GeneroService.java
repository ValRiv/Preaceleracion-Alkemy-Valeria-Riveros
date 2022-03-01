
package com.challenge.prealkemy.service;

import com.challenge.prealkemy.dto.GeneroDTO;
import com.challenge.prealkemy.dto.GeneroDTOBasic;
import com.challenge.prealkemy.entity.GeneroEntity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Service
public interface GeneroService {
     GeneroDTO saveGenero(GeneroDTO generoDTO);

    List<GeneroDTO> getAllGeneros();

    List<GeneroDTOBasic> getAllGenerosBasic();

    GeneroDTO modifyGenero(String id, GeneroDTO generoDTO);

    void deleteGeneroById(String id);

    GeneroEntity getGeneroById(String id);
}
