package com.challenge.prealkemy.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author river
 */
@Getter
@Setter
public class PeliculaDTOFilter {

    private String titulo;
    private List<String> generos;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
 public PeliculaDTOFilter( String titulo, List<String> generos, String order) {
     
        this.titulo = titulo;

        this.generos = generos;
        this.order = order;
    }
}
