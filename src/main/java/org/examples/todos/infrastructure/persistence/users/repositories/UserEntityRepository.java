package org.examples.todos.infrastructure.persistence.users.repositories;

import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    
}
