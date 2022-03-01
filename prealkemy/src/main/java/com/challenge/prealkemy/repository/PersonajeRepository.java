
package com.challenge.prealkemy.repository;

import com.challenge.prealkemy.entity.PersonajeEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author river
 */
@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity, String> {

    List<PersonajeEntity> findAll(Specification<PersonajeEntity> entitySpecification);
     
    
}
