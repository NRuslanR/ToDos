package org.examples.todos.application.todos.accounting;

import java.time.LocalDateTime;
import org.examples.todos.application.users.accounting.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto
{
	private long id;
	private int priority;
	private String name; 
	private String description;
	private LocalDateTime creationDate;
	private LocalDateTime performingDate;
	private UserDto author;
	private Iterable<ToDoDto> subToDos;
	private Iterable<ToDoNoteDto> notes;
}
