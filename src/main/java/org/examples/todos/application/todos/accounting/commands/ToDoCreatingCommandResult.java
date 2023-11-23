package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.todos.accounting.ToDoInfoDto;

public record ToDoCreatingCommandResult(
	ToDoInfoDto toDoInfo
)
{
	
}
