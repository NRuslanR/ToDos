package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.Data;

@Data
public class ToDoCreatingCommand extends ServiceCommand 
{
	private int priority;
	private String name; 
	private String description;
	private long authorId;
	private Iterable<ToDoNoteCreatingCommand> noteCommands;
}
