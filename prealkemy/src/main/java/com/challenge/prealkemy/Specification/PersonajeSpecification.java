
package com.challenge.prealkemy.Specification;

import com.challenge.prealkemy.dto.PersonajeDTOFilter;
import com.challenge.prealkemy.entity.PeliculaEntity;
import com.challenge.prealkemy.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author river
 */
public class PersonajeSpecification {
   
    public Specification<PersonajeEntity> getByFilters(PersonajeDTOFilter filterDTO) {

     
        return (root, query, criteriaBuilder) -> {

        
            List<Predicate> predicates = new ArrayList<>();

         
            if (StringUtils.hasLength(filterDTO.getNombre())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filterDTO.getNombre().toLowerCase() + "%"));
            }

      
            if (filterDTO.getEdad() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("edad"), filterDTO.getEdad())
                );
            }

      
            if (filterDTO.getPeso() != null) {
                predicates.add(
                        criteriaBuilder.like(
                                root.get("peso").as(String.class),
                                "%" + filterDTO.getPeso() + "%"
                        )
                );
            }

        
            if (!CollectionUtils.isEmpty(filterDTO.getPeliculas())) {
                Join<PersonajeEntity, PeliculaEntity> join = root.join("personajeMovies", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filterDTO.getPeliculas()));
            }

       
            query.distinct(true);

            
            String orderByField = "nombre";
            query.orderBy(
                    filterDTO.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField))
            );

            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}


