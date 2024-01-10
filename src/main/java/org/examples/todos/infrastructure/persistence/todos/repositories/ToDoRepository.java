package org.examples.todos.infrastructure.persistence.todos.repositories;

import java.util.UUID;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.infrastructure.persistence.common.repositories.DomainEntityRepository;

public interface ToDoRepository extends DomainEntityRepository<ToDo, UUID>
{
    
}
