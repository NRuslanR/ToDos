package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.UUID;

public class ToDoNoteTestUtils 
{
	public static ToDoNote createSimpleToDoNote(String name, String text)
	{
		return new ToDoNote(createSimpleToDoNoteInfo(name, text));
	}

	public static ToDoNoteInfo createSimpleToDoNoteInfo(String name, String text) {
	
		return new ToDoNoteInfo(UUID.randomUUID(), name, text, LocalDateTime.now());
	}
}
