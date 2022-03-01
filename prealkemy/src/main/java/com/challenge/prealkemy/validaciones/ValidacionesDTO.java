
package com.challenge.prealkemy.validaciones;

import com.challenge.prealkemy.dto.GeneroDTO;
import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PersonajeDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author river
 */
@Component
public class ValidacionesDTO {
    
     public boolean peliculaDTOValido(PeliculaDTO peliculaDTO) {
        
         boolean valido = false;
       
         if (peliculaDTO.getImagen() != null &&
                peliculaDTO.getTitulo() != null &&
                peliculaDTO.getFechaDeCreacion()!= null &&
                peliculaDTO.getCalificacion()>= 1 &&
                peliculaDTO.getCalificacion() <= 5 &&
                peliculaDTO.getPersonajes() != null &&
                peliculaDTO.getGeneros() != null) {
            if (!peliculaDTO.getPersonajes().isEmpty()) {
                valido = iteratePersonajes(peliculaDTO.getPersonajes());
            }
            if (!peliculaDTO.getPersonajes().isEmpty()) {
                valido = iterateGeneros(peliculaDTO.getGeneros());
            }
        }
        return valido;
    }

    private boolean iteratePersonajes(List<PersonajeDTO> personajeDTO) {
        for (PersonajeDTO personajeDto : personajeDTO) {
            if (!personajeDTOValido(personajeDto)) {
                return false;
            }
        }
        return true;
    }

    public boolean personajeDTOValido(PersonajeDTO personajeDto) {
        return personajeDto.getImagen() != null &&
               personajeDto.getNombre() != null &&
                personajeDto.getHistoria()!= null &&
                personajeDto.getEdad() > 0 &&
                personajeDto.getPeso() > 0;
    }

    private boolean iterateGeneros(List<GeneroDTO> generos) {
        for (GeneroDTO generoDTO : generos) {
            if (!generoDTOValido(generoDTO)) {
                return false;
            }
        }
        return true;
    }

    public boolean generoDTOValido(GeneroDTO generoDTO) {
        return generoDTO.getImagen() != null &&
                generoDTO.getNombre() != null;
    }

    public boolean peliculaDTOUpdateValido(PeliculaDTO peliculaDTO) {
        return peliculaDTO.getImagen() != null &&
                peliculaDTO.getTitulo() != null &&
                peliculaDTO.getFechaDeCreacion()!= null &&
                peliculaDTO.getCalificacion()>= 1 &&
                peliculaDTO.getCalificacion()<= 5;
    }
}


