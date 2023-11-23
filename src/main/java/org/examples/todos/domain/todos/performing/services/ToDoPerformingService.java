package org.examples.todos.domain.todos.performing.services;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.todos.actors.ToDo;

public interface ToDoPerformingService {
    
    void performToDo(ToDo toDo) throws DomainException;
}
