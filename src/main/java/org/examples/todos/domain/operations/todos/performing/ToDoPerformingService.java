package org.examples.todos.domain.operations.todos.performing;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;

public interface ToDoPerformingService {
    
    ToDoList performToDo(ToDo toDo) throws DomainException;
}
