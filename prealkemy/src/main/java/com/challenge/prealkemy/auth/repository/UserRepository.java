
package com.challenge.prealkemy.auth.repository;

import com.challenge.prealkemy.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author river
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUsername(String username);

    
}
