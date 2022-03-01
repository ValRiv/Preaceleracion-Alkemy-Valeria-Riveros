
package com.challenge.prealkemy.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author river
 */
@Getter
@Setter
public class GeneroDTO {
     private String id;
    private String nombre;
    private String imagen;
    
    private List<PeliculaDTO> peliculas;

}
