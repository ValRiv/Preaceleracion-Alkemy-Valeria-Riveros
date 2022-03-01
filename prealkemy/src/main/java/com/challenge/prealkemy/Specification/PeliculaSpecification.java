/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.prealkemy.Specification;

import com.challenge.prealkemy.dto.PeliculaDTOFilter;
import com.challenge.prealkemy.entity.GeneroEntity;
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
public class PeliculaSpecification {
    
    public Specification<PeliculaEntity> getFiltered(PeliculaDTOFilter peliculaFilters) {

        
        return (root, query, criteriaBuilder) -> {

           
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(peliculaFilters.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + peliculaFilters.getTitulo().toLowerCase() + "%"
                        )
                );
            }

          
            if (!CollectionUtils.isEmpty(peliculaFilters.getPersonajes())) {
                Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculaPersonajes", JoinType.INNER);
                Expression<String> personajesId = join.get("id");
                predicates.add(personajesId.in(peliculaFilters.getPersonajes()));
            }
            
          
            if (!CollectionUtils.isEmpty(peliculaFilters.getGeneros())) {
                Join<PeliculaEntity, GeneroEntity> join = root.join("peliculaGeneros", JoinType.INNER);
                Expression<String> generosId = join.get("id");
                predicates.add(generosId.in(peliculaFilters.getGeneros()));
            }

      
            query.distinct(true);

       
            String orderByField = "titulo";
            query.orderBy(
                    peliculaFilters.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField))
            );

          
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}


