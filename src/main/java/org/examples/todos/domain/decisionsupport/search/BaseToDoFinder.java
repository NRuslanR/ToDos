package org.examples.todos.domain.decisionsupport.search;

import java.util.Objects;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;

public abstract class BaseToDoFinder implements ToDoFinder
{

	@Override
	public ToDoList findAllSubToDosRecursivelyFor(ToDo targetToDo) 
			throws NullPointerException, DomainException
	{
		Objects.requireNonNull(targetToDo);
		
		return doFindAllSubToDosRecursivelyFor(targetToDo);
	}

	protected abstract ToDoList doFindAllSubToDosRecursivelyFor(ToDo targetToDo) throws DomainException;

	@Override
	public ToDoList findUserTodos(User author) 
			throws NullPointerException, DomainException 
	{
		Objects.requireNonNull(author);
		
		return doFindUserTodos(author);
	}

	protected abstract ToDoList doFindUserTodos(User author) throws DomainException;
	
}
