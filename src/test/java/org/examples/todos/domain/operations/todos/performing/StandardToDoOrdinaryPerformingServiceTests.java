package org.examples.todos.domain.operations.todos.performing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoTestUtils;
import org.examples.todos.domain.operations.todos.forming.StandardToDoFormer;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StandardToDoOrdinaryPerformingServiceTests 
{
	private static ToDoOrdinaryPerformingService toDoOrdinaryPerformingService;
	private static ToDoFormer toDoFormer;
	
	@BeforeAll
	public static void setupTestSuite()
	{
		toDoOrdinaryPerformingService = new StandardToDoOrdinaryPerformingService();
		toDoFormer = new StandardToDoFormer();
	}
	
	@Test
	public void should_Return_ListWithSinglePerformedToDo_When_ToDoForPerformingIsValid()
	{
		var actor = UserTestUtils.createSimpleUserWithResetClaims();
		var toDoInfo = ToDoTestUtils.createSimpleToDoInfo("To-Do", ToDoPriority.Low(0), actor);
		
		var toDo = toDoFormer.formToDo(toDoInfo, actor);
		
		var performedToDoList = toDoOrdinaryPerformingService.performToDo(toDo);
		
		assertEquals(1, performedToDoList.count());
		assertNotNull(performedToDoList.getById(toDo.getId()));
	}
}
