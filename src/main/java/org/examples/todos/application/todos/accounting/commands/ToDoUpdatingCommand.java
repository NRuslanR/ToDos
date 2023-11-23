package org.examples.todos.application.todos.accounting.commands;

import java.time.LocalDateTime;
import java.util.Optional;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.Data;

@Data
public class ToDoUpdatingCommand extends ServiceCommand
{
	private long toDoId;
	private int priority;
	private String name; 
	private String description;
	private Optional<Long> authorId;
	private Optional<LocalDateTime> performingDate;
	private Iterable<ToDoUpdatingCommand> subToDoUpdatingCommands;
	private Iterable<ToDoNoteUpdatingCommand> noteUpdatingCommands;
}
