package org.examples.todos.infrastructure.persistence.todos.repositories;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaToDoEntityRepository extends JpaRepository<ToDoEntity, UUID>
{

}
