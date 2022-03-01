
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
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTOFilter {
      private String titulo;
    private List<String> personajes;
    private List<String> generos;
    private String order;



    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }

}


