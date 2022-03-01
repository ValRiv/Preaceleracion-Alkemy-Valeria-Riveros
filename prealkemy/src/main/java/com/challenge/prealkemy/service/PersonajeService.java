
package com.challenge.prealkemy.service;

import com.challenge.prealkemy.dto.PersonajeDTO;
import com.challenge.prealkemy.dto.PersonajeDTOBasic;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Service

public interface PersonajeService {
    
     public PersonajeDTO savePersonaje(PersonajeDTO personajeDTO);

    public PersonajeDTO modifyPersonaje(String idPersonaje, PersonajeDTO personajeDTO);

    public void deletePersonaje(String idPersonaje);

    List<PersonajeDTOBasic> getAllPersonajeBasic();

    List<PersonajeDTO> getAllPersonaje();

    List<PersonajeDTO> getPersonajeByFilters(String nombre, Integer edad, Double peso, List<String> peliculas, String order);

    PersonajeDTO getPersonajeById(String idPersonaje);
}
