package org.examples.todos.domain.operations.todos.forming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoTestUtils;
import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.doubles.StubToDoFinder;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StandardToDoParentAssigningServiceTests 
{
	private static ToDo targetToDo;
	private static ToDoList toDoList;
	private static ToDoParentAssigningService toDoParentAssigningService;
	
	@BeforeAll
	public static void setup()
	{
		var toDoFormer = new StandardToDoFormer();
		
		ToDoInfo targetToDoInfo = createTestToDoInfo("To-Do#1");
		
		targetToDo = toDoFormer.formToDo(targetToDoInfo, targetToDoInfo.getAuthor());
		
		toDoList = createTestToDoList(targetToDo);
		
		var toDoFinder = new StubToDoFinder(toDoList);
		
		toDoParentAssigningService = new StandardToDoParentAssigningService(toDoFinder);
	}
	
	@Test
	public void should_Assign_ToDoParent_When_Parent_Is_Not_SubToDo()
	{
		var parentToDo = createTestToDo("To-Do#2");
		
		toDoParentAssigningService.assignToDoParent(targetToDo, parentToDo);
		
		assertEquals(targetToDo.getParentToDoId(), parentToDo.getId());
	}

	@ParameterizedTest
	@ValueSource(strings = { "To-Do#1", "To-Do#1.1", "To-Do#1.2", "To-Do#1.2.1", "To-Do#1.2.2" })
	public void should_ThrowException_When_AssignableParent_Is_SubToDo_Of_TargetToDo(String toDoName)
	{
		var parentToDo = toDoList.findByName(toDoName).get();
		
		assertThrows(
			DomainException.class, 
			() -> {
				
				toDoParentAssigningService.assignToDoParent(targetToDo, parentToDo);
			}
		);
	}
	
	private static ToDoList createTestToDoList(ToDo root) 
	{
		var toDoList = new ToDoList();
		
		toDoList.add(root);
		
		var parentToDo = root;
		
		var toDo = createTestToDo("To-Do#1.1", parentToDo.getId());
		
		toDoList.add(toDo);
		
		toDo = createTestToDo("To-Do#1.2", parentToDo.getId());
		
		toDoList.add(toDo);
		
		parentToDo = toDo;
		
		toDo = createTestToDo("To-Do#1.2.1", parentToDo.getId());
		
		toDoList.add(toDo);

		toDo = createTestToDo("To-Do#1.2.2", parentToDo.getId());
		
		toDoList.add(toDo);
		
		return toDoList;
	}
	
	private static ToDo createTestToDo(String name, UUID parentToDoId)
	{
		return new ToDo(createTestToDoInfo(name, parentToDoId));
	}
	
	private static ToDo createTestToDo(String name)
	{
		return new ToDo(createTestToDoInfo(name));
	}
	
	private static ToDoInfo createTestToDoInfo(String name, UUID parentToDoId)
	{
		var toDoInfo = createTestToDoInfo(name);
		
		toDoInfo.setParentToDoId(Intention.of(parentToDoId));
		
		return toDoInfo;
	}
	
	private static ToDoInfo createTestToDoInfo(String name)
	{
		return ToDoTestUtils.createSimpleToDoInfo(
			name, ToDoPriority.Medium(15), createTestToDoAuthor()
		);
	}
	
	private static User createTestToDoAuthor()
	{
		return UserTestUtils.createSimpleUserWithResetClaims();
	}
}
