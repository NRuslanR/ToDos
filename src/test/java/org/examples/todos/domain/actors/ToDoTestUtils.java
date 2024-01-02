package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.domain.resources.users.User;

public class ToDoTestUtils 
{
	public static ToDo createSimpleToDo(String name, ToDoPriority priority, User author)
	{
		return new ToDo(createSimpleToDoInfo(name, priority, author));
	}
	
	public static ToDoInfo createSimpleToDoInfo(String name, ToDoPriority priority, User author)
	{
		return new ToDoInfo(UUID.randomUUID(), name, priority, LocalDateTime.now(), author);
	}
}
