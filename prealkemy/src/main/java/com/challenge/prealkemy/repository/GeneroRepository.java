
package com.challenge.prealkemy.repository;

import com.challenge.prealkemy.entity.GeneroEntity;
import com.challenge.prealkemy.entity.PeliculaEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author river
 */
@Repository
public interface GeneroRepository extends JpaRepository<GeneroEntity, String> {
     List<PeliculaEntity> findAll(Specification<PeliculaEntity> entitySpecification);
}

