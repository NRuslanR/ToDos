package org.examples.todos.application.todos.accounting.commands;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoNoteCreatingCommand extends ServiceCommand {

	private String note;
}
