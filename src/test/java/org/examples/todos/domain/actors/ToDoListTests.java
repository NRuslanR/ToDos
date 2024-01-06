package org.examples.todos.domain.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToDoListTests 
{
	private static Map<User, Collection<ToDo>> usersTodos;
	
	private ToDoList toDoList;
	
	@BeforeAll
	public static void setupSuite()
	{
		usersTodos = createTestUsersToDosMap();
	}
	

	private static Map<User, Collection<ToDo>> createTestUsersToDosMap() 
	{
		var usersToDosMap = new HashMap<User, Collection<ToDo>>();
		
		var user = UserTestUtils.createSimpleUserWithResetClaims();
		
		var toDos = new ArrayList<ToDo>();
		
		toDos.add(ToDoTestUtils.createSimpleToDoWithDefaultLowPriority("To-Do#1", user));
		toDos.add(ToDoTestUtils.createSimpleToDoWithDefaultMediumPriority("To-Do#2", user));
		
		usersToDosMap.put(user, toDos);
		
		toDos = new ArrayList<>();
		
		user = UserTestUtils.createSimpleUserWithResetClaims();
		
		toDos.add(ToDoTestUtils.createSimpleToDoWithDefaultUrgentPriority("To-Do#3", user));
		
		var toDo = ToDoTestUtils.createSimpleToDoWithDefaultLowPriority("To-Do#4", user);
		
		toDos.add(toDo);
		toDos.add(ToDoTestUtils.createSimpleToDoWithDefaultMediumPriority("To-Do#4.1", toDo.getId(), user));
		
		usersToDosMap.put(user, toDos);
		
		return usersToDosMap;
	}


	@BeforeEach
	public void setup()
	{
		toDoList = createTestToDoList();
	}

	private ToDoList createTestToDoList() 
	{
		return new ToDoList(usersTodos.values().stream().flatMap(Item -> Item.stream()).toList());
	}
	
	@Test
	public void should_ReturnToDoById_When_ItIsContainedInList()
	{
		var toDoId = getIdOfAnyToDos();
		
		var toDo = toDoList.getById(toDoId);
		
		assertNotNull(toDo);
		assertEquals(toDoId, toDo.getId());
	}
	
	@Test
	public void should_ThrowException_When_TryingReturnToDoById_And_ItIsNotContainedInList()
	{
		assertThrows(DomainException.class, () -> {
			
			toDoList.getById(UUID.randomUUID());
			
		});
	}
	
	@Test
	public void should_ReturnChildToDosForParent_When_TheyAreContainedInList()
	{
		var toDo = toDoList.getByName("To-Do#4");
		
		var childToDoList = toDoList.getAllSubToDosFor(toDo);
		
		var userToDos = usersTodos.get(toDo.getAuthor());
		
		assertNotNull(childToDoList);
		assertEquals(1, childToDoList.count());
		assertTrue(CollectionUtils.containsAll(userToDos, IterableUtils.toList(childToDoList)));
	}
	
	@Test
	public void should_ReturnToDosForUser_When_TheyAreContainedInList()
	{
		var toDo = toDoList.getByName("To-Do#1");
		
		var userToDoList = toDoList.getAllUserToDos(toDo.getAuthor());
		
		var userToDos = usersTodos.get(toDo.getAuthor());
		
		assertNotNull(userToDoList);
		assertEquals(userToDos.size(), userToDoList.count());
		assertTrue(CollectionUtils.containsAll(userToDos, IterableUtils.toList(userToDoList)));
	}
	
	@Test
	public void should_AddToDo_When_ItIsNotContainedInList()
	{
		var toDo = createTestSimpleToDo("To-Do#New");
		
		toDoList.add(toDo);
		
		var addedToDo = toDoList.getById(toDo.getId());
		
		assertEquals(toDo, addedToDo);
	}
	
	@Test
	public void should_ThrowException_When_TryingAddToDo_And_ItIsAlreadyContainedInList()
	{
		var existingToDo = toDoList.getById(getIdOfAnyToDos());
		
		assertThrows(DomainException.class, () -> {
			
			toDoList.add(existingToDo);
			
		});
	}
	
	@Test
	public void should_RemoveToDo_When_ItIsContainedInList()
	{
		var toDo = toDoList.getById(getIdOfAnyToDos());
		
		toDoList.remove(toDo);
		
		assertFalse(toDoList.contains(toDo));
	}
	
	private static UUID getIdOfAnyToDos()
	{
		return usersTodos.values().stream().findAny().get().stream().findAny().get().getId();
	}
	
	private static ToDo createTestSimpleToDo(String name)
	{
		return ToDoTestUtils.createSimpleToDoWithDefaultLowPriority(
			name, UserTestUtils.createSimpleUserWithResetClaims()
		);
	}
}
