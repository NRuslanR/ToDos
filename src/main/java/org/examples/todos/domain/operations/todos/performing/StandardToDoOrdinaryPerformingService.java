package org.examples.todos.domain.operations.todos.performing;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;

public class StandardToDoOrdinaryPerformingService implements ToDoOrdinaryPerformingService 
{

	@Override
	public ToDoList performToDo(ToDo toDo) throws DomainException {
		
		toDo.perform();
		
		return ToDoList.of(toDo);
		
	}

}
