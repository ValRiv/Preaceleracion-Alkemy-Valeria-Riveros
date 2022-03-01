
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
public class PersonajeDTO {
    
    private String id;
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;

    private List<PeliculaDTO> peliculas;

}


