package org.examples.todos.domain.todos.performing.services;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.todos.actors.ToDoList;

public class StandardToDoOrdinaryPerformingService implements ToDoOrdinaryPerformingService 
{

	@Override
	public ToDoList performToDo(ToDo toDo) throws DomainException {
		
		toDo.perform();
		
		return ToDoList.of(toDo);
		
	}

}
