package org.examples.todos.application.todos.accounting.commands;

import java.time.LocalDateTime;
import java.util.Optional;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoNoteUpdatingCommand extends ServiceCommand {
	
	private long noteId;
	private String note;
	private Optional<LocalDateTime> creatinDateTime;
	
}
