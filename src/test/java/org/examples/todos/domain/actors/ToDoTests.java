package org.examples.todos.domain.actors;

import org.examples.todos.domain.operations.todos.forming.StandardToDoFormer;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ToDoTests 
{
	private static ToDoFormer toDoFormer;
	private static User actor;
	
	private ToDo testToDo;
	
	@BeforeAll
	public static void setupTestSuite()
	{
		toDoFormer = new StandardToDoFormer();
		actor = UserTestUtils.createSimpleUserWithClaims(1, 1, false, false);
	}
	
	@BeforeEach
	public void setupTest()
	{
		var testToDoInfo = ToDoTestUtils.createSimpleToDoInfo("To-Do", ToDoPriority.Medium(10), actor);
		
		testToDo = toDoFormer.formToDo(testToDoInfo, actor);
	}
}
