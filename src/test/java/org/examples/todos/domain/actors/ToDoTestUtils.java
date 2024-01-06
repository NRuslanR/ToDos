package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.resources.users.User;

public class ToDoTestUtils 
{
	public static ToDo createSimplePerformedToDoWithRandomPriority(
		String name, 
		User author, 
		LocalDateTime performingDateTime
	)
	{
		var toDoInfo = createSimpleToDoInfo(name, ToDoPriority.Medium(15), author);
		
		toDoInfo.setPerformingDate(Intention.of(performingDateTime));
		
		return new ToDo(toDoInfo);
	}
	
	public static ToDo createSimpleToDoWithDefaultLowPriority(String name, UUID parentToDoId, User author)
	{
		return createSimpleToDo(name, parentToDoId, ToDoPriority.Low(0), author);
	}
	
	public static ToDo createSimpleToDoWithDefaultMediumPriority(String name, UUID parentToDoId, User author)
	{
		return createSimpleToDo(name, parentToDoId, ToDoPriority.Medium(0), author);
	}
	
	public static ToDo createSimpleToDoWithDefaultUrgentPriority(String name, UUID parentToDoId, User author)
	{
		return createSimpleToDo(name, parentToDoId, ToDoPriority.Urgent(0), author);
	}
	
	public static ToDo createSimpleToDo(String name, UUID parentToDoId, ToDoPriority priority, User author)
	{
		return new ToDo(createSimpleToDoInfo(name, parentToDoId, priority, author));
	}
	
	public static ToDo createSimpleToDoWithDefaultLowPriority(String name, User author)
	{
		return createSimpleToDo(name, ToDoPriority.Low(0), author);
	}
	
	public static ToDo createSimpleToDoWithDefaultMediumPriority(String name, User author)
	{
		return createSimpleToDo(name, ToDoPriority.Medium(0), author);
	}
	
	public static ToDo createSimpleToDoWithDefaultUrgentPriority(String name, User author)
	{
		return createSimpleToDo(name, ToDoPriority.Urgent(0), author);
	}
	
	public static ToDo createSimpleToDo(String name, ToDoPriority priority, User author)
	{
		return new ToDo(createSimpleToDoInfo(name, priority, author));
	}
	
	public static ToDoInfo createSimpleToDoInfo(
		String name, UUID parentToDoId, ToDoPriority priority, User author
	)
	{
		var toDoInfo = createSimpleToDoInfo(name, priority, author);
	
		toDoInfo.setParentToDoId(Intention.of(parentToDoId));
		
		return toDoInfo;
	}
	
	public static ToDoInfo createSimpleToDoInfo(String name, ToDoPriority priority, User author)
	{
		return new ToDoInfo(UUID.randomUUID(), name, priority, LocalDateTime.now(), author);
	}
}
