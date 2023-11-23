package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRemovingCommand extends ServiceCommand {
	
	private long toDoId;

	public static ToDoRemovingCommand of(long toDoId)
	{
		return new ToDoRemovingCommand(toDoId);
	}
}
