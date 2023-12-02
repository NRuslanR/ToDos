package org.examples.todos.domain.operations.todos.forming;

import java.util.UUID;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;
import org.examples.todos.domain.resources.users.User;

public class StandardToDoCreationService implements ToDoCreationService {

	private final ToDoFinder toDoFinder;
	private final ToDoFormer toDoFormer;
	
	public StandardToDoCreationService(ToDoFinder toDoFinder, ToDoFormer toDoFormer)
	{
		this.toDoFinder = toDoFinder;
		this.toDoFormer = toDoFormer;
	}
	
	@Override
	public ToDo createToDoFor(ToDoInfo toDoInfo, User author) throws DomainException {
		
		ensureToDoCanCreatedForUser(toDoInfo, author);
		
		toDoInfo.setId(UUID.randomUUID());
		
		return toDoFormer.formToDo(toDoInfo, author);
	}

	private void ensureToDoCanCreatedForUser(ToDoInfo toDoInfo, User author) {
		
		var userToDoList = toDoFinder.findUserTodos(author);
		
		ensureCreatedToDoCountLimitNotReached(userToDoList, author);
		ensureToDoNotCreatedYet(userToDoList, toDoInfo);
		//ensureToDoParentExistIfNecessary(toDoInfo);
	}

	private void ensureCreatedToDoCountLimitNotReached(ToDoList userToDoList, User author) {
		
		if (author.getAllowedToDoCreationCount() >= userToDoList.count())
		{
			throw new DomainException("The created To-Do count limit is reached");
		}
	}

	private void ensureToDoNotCreatedYet(ToDoList userToDoList, ToDoInfo toDoInfo) {
		
		if (userToDoList.containsByName(toDoInfo.getName()))
		{
			throw new DomainException("To-Do \"" + toDoInfo.getName() + "\" is already created");
		}
		
	}

}
