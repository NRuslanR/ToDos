package org.examples.todos.domain.todos.performing.services;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.todos.actors.ToDoList;

public interface ToDoPerformingService {
    
    ToDoList performToDo(ToDo toDo) throws DomainException;
}
