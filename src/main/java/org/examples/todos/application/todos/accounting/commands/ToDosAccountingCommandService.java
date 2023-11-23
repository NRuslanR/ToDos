package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;

public interface ToDosAccountingCommandService {
	
	ToDoCreatingCommandResult createToDo(
		ToDoCreatingCommand command
	) throws NoResourceAccessRightsException;

	ToDoUpdatingCommandResult updateToDo(
		ToDoUpdatingCommand command
	) throws ResourceNotFoundException, NoResourceAccessRightsException;

	ToDoRemovingCommandResult removeToDo(
		ToDoRemovingCommand command
	) throws NoResourceAccessRightsException;
}
