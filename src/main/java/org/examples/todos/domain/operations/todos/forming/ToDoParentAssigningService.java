package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.errors.DomainException;

public interface ToDoParentAssigningService 
{
	void assignToDoParent(ToDo targetToDo, ToDo parentToDo) throws DomainException;
}
