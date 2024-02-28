package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.todos.accounting.ToDoInfoDto;

public record ToDoUpdatingCommandResult(
	ToDoInfoDto toDoInfo
) 
{

}
