package org.examples.todos.infrastructure.persistence.todos.repositories;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.BaseToDoFinder;
import org.examples.todos.domain.resources.users.User;

public class RepositoryToDoFinder extends BaseToDoFinder
{
	private final ToDoRepository repository;
	
	public RepositoryToDoFinder(ToDoRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected ToDoList doFindAllSubToDosRecursivelyFor(ToDo targetToDo) throws DomainException {
	
		return null;
	}

	@Override
	protected ToDoList doFindUserTodos(User author) throws DomainException {

		return null;
	}
}
