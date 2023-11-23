package org.examples.todos.application.todos.accounting;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoNoteDto {
	
	private long id; 
	private String note;
	private LocalDateTime creationDateTime;
	
}
