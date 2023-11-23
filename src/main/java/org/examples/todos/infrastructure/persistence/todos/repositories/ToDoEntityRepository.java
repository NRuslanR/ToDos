package org.examples.todos.infrastructure.persistence.todos.repositories;

import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoEntityRepository extends JpaRepository<ToDoEntity, Long> {
    
}
