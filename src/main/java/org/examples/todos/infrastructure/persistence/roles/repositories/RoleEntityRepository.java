package org.examples.todos.infrastructure.persistence.roles.repositories;

import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long>   {
    
}
