package com.challenge.prealkemy.auth.builder;

import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PersonajeDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author river
 */
@Getter
@Setter
public class PersonajeBuilder {

    private PersonajeDTO personajeDTO;

    public PersonajeBuilder() {
        this.personajeDTO = new PersonajeDTO();
    }

    public PersonajeBuilder id(String id) {
        this.personajeDTO.setId(id);
        return this;
    }

    public PersonajeBuilder imagen(String imagen) {
        this.personajeDTO.setImagen(imagen);
        return this;
    }

    public PersonajeBuilder nombre(String nombre) {
        this.personajeDTO.setNombre(nombre);
        return this;
    }

    public PersonajeBuilder edad(Integer Edad) {
        this.personajeDTO.setEdad(Edad);
        return this;
    }

    public PersonajeBuilder historia(String historia) {
        this.personajeDTO.setHistoria(historia);
        return this;
    }

    public PersonajeBuilder pelicula(List<PeliculaDTO> peliculas) {
        this.personajeDTO.setPeliculas(peliculas);
        return this;
    }

}
