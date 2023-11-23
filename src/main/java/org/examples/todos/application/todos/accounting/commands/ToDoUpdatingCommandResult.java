package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.todos.accounting.ToDoAccessRightsDto;
import org.examples.todos.application.todos.accounting.ToDoDto;
import org.examples.todos.application.todos.accounting.ToDoInfoDto;

public record ToDoUpdatingCommandResult(
	ToDoInfoDto toDoInfo
) 
{

}
