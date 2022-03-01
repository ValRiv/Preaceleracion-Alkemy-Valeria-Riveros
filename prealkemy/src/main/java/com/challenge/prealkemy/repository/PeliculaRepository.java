
package com.challenge.prealkemy.repository;

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
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, String>{
     List<PeliculaEntity> findAll(Specification<PeliculaEntity> entitySpecification);
}
