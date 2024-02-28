package org.examples.todos.domain.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ToDoNoteListTests 
{
	private ToDoNoteList toDoNoteList;
	
	@BeforeEach
	public void setup()
	{
		toDoNoteList = createTestToDoNoteList();
	}
	
	@ParameterizedTest
	@MethodSource("validToDoNoteListInput")
	public void should_Be_Created_ToDoNoteList_WhenInputValid(Collection<ToDoNote> input)
	{
		var toDoNoteList = new ToDoNoteList(input);
		
		assertIterableEquals(input, toDoNoteList);
	}
	
	public static Stream<Arguments> validToDoNoteListInput()
	{
		return Stream.of(Arguments.of(createTestToDoNoteCollection()));
	}
	
	@Test
	public void should_AddToDoNote_WhenList_DoesNotContainsIt()
	{
		var toDoNoteList = createTestToDoNoteList();
		
		var toDoNoteForAdd = ToDoNoteTestUtils.createSimpleToDoNote("Note#4", "text");
		
		toDoNoteList.add(toDoNoteForAdd);
		
		var addedToDoNote = toDoNoteList.getById(toDoNoteForAdd.getId());
		
		assertEquals(toDoNoteForAdd, addedToDoNote);
	}
	
	@Test
	public void should_ThrowException_When_AlreadyContainedToDoNote_IsAdding()
	{
		assertThrows(
			DomainException.class, 
			() -> {
				
				toDoNoteList.add(ToDoNoteTestUtils.createSimpleToDoNote("Note#3", "text"));
			}
		);
	}
	
	@Test
	public void should_AddToDoNotes_When_List_DoesNotContainsThem()
	{
		var toDoNotesForAdd = 
			Arrays.asList(
				ToDoNoteTestUtils.createSimpleToDoNote("Note#4", "text"),
				ToDoNoteTestUtils.createSimpleToDoNote("Note#5", "text")
			);
		
		toDoNoteList.addAll(toDoNotesForAdd);
		
		var addedToDoNotes = 
			Arrays.asList(
				toDoNoteList.getById(toDoNotesForAdd.get(0).getId()),
				toDoNoteList.getByName(toDoNotesForAdd.get(1).getName())
			);
		
		assertIterableEquals(toDoNotesForAdd, addedToDoNotes);
	}
	
	@Test
	public void should_ThrowException_When_AnyOfToDoNotesForAdd_IsContainedInList()
	{
		var toDoNotesForAdd = 
			Arrays.asList(
				ToDoNoteTestUtils.createSimpleToDoNote("Note#4", "text"),
				ToDoNoteTestUtils.createSimpleToDoNote("Note#3", "text")
			);
		
		assertThrows(
			DomainException.class,
			() -> {
				
				toDoNoteList.addAll(toDoNotesForAdd);
			}
		);
	}
	
	@Test
	public void should_Returns_ToDoNoteById_WhenListContainsIt()
	{
		var todoNote = toDoNoteList.getById(toDoNoteList.get(0).getId());
		
		assertNotNull(todoNote);
	}
	
	@Test
	public void should_ThrowException_When_NotContainedToDoNote_IsGettingById_FromList()
	{
		assertThrows(
			DomainException.class, 
			() -> {
				
				toDoNoteList.getById(UUID.randomUUID());
				
			}
		);
	}
	
	@Test
	public void should_Returns_ToDoNoteByName_WhenListContainsIt()
	{
		var todoNote = toDoNoteList.getByName(toDoNoteList.get(0).getName());
		
		assertNotNull(todoNote);
	}
	
	@Test
	public void should_ThrowException_When_NotContainedToDoNote_IsGettingByName_FromList()
	{
		assertThrows(
			DomainException.class, 
			() -> {
				
				toDoNoteList.getByName("");
				
			}
		);
	}
	
	@Test
	public void should_RemoveToDoNoteById_WhenList_ContainsIt()
	{
		var toDoNoteIdForRemove = toDoNoteList.get(0).getId();
		
		toDoNoteList.removeById(toDoNoteIdForRemove);
		
		assertThrows(DomainException.class, () -> {
			
			toDoNoteList.getById(toDoNoteIdForRemove);
			
		});
	}
	
	@Test
	public void should_RemoveToDoNoteByName_WhenList_ContainsIt()
	{
		var toDoNoteNameForRemove = toDoNoteList.get(0).getName();
		
		toDoNoteList.removeByName(toDoNoteNameForRemove);
		
		assertThrows(DomainException.class, () -> {
			
			toDoNoteList.getByName(toDoNoteNameForRemove);
			
		});
	}
	
	private static ToDoNoteList createTestToDoNoteList()
	{
		return new ToDoNoteList(createTestToDoNoteCollection());
	}
	
	private static Collection<ToDoNote> createTestToDoNoteCollection()
	{
		var toDoNoteList = new ArrayList<ToDoNote>();
		
		toDoNoteList.add(ToDoNoteTestUtils.createSimpleToDoNote("Note#1", "text"));
		toDoNoteList.add(ToDoNoteTestUtils.createSimpleToDoNote("Note#2", "text"));
		toDoNoteList.add(ToDoNoteTestUtils.createSimpleToDoNote("Note#3", "text"));
		
		return toDoNoteList;
	}
}
