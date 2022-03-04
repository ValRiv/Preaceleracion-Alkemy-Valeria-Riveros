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

public class PersonajeDTOFilter {

    private String nombre;

    private Integer edad;

    private List<String> peliculas;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }

    public PersonajeDTOFilter(String nombre, Integer edad, List<String> peliculas, String order) {
        this.nombre = nombre;
        this.edad = edad;
        this.peliculas = peliculas;
        this.order = order;
    }
}
