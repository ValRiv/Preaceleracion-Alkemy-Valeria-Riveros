
package com.challenge.prealkemy.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author river
 */
@Setter
@Getter
public class PeliculaDTO {
    private String id;
    private String imagen;
    private String titulo;
    private String fechaDeCreacion;
    private float calificacion;

    private List<PersonajeDTO> personajes;
    private List<GeneroDTO> generos;

}


