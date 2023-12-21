package org.examples.todos.domain.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ToDoNoteTests 
{	
	private ToDoNote toDoNote;
	
	@BeforeEach
	public void setup()
	{
		toDoNote = createTestToDoNote();
	}
	
	@ParameterizedTest
	@MethodSource("validToDoNoteInfos")
	public void should_Be_Created_CorrectToDoNote_When_InfoValid(ToDoNoteInfo info)
	{
		var toDoNote = new ToDoNote(info);
		
		assertEquals(info.getId(), toDoNote.getId());
		assertEquals(info.getName(), toDoNote.getName());
		assertEquals(info.getText(), toDoNote.getText());
		assertEquals(info.getCreatedAt(), toDoNote.getCreatedAt());
	}
	
	private static Stream<Arguments> validToDoNoteInfos()
	{
		var infos = new ArrayList<ToDoNoteInfo>();
		
		var info = new ToDoNoteInfo();
		
		info.setId(UUID.randomUUID());
		info.setName("a");
		info.setText("some text");
		info.setCreatedAt(LocalDateTime.now());
		
		infos.add(info);
		
		return infos.stream().map(Arguments::of);
	}
	
	@Test
	public void should_Be_Changed_ToDoNoteName_WhenAssignableValueNotEmpty()
	{
		final var newName = "newName";
		
		toDoNote.setName(newName);
		
		assertEquals(newName, toDoNote.getName());
	}
	
	@Test
	public void should_ThrowException_When_ToDoNoteNameEmpty()
	{
		assertThrows(DomainException.class, () -> {
			
			toDoNote.setName(null);
			
		});
	}
	
	@Test
	public void should_Be_Changed_ToDoNoteText_WhenAssignableValueNotEmpty()
	{
		final var newText = "newText";
		
		toDoNote.setText(newText);
		
		assertEquals(newText, toDoNote.getText());
	}
	
	@Test
	public void should_ThrowException_When_ToDoNoteTextEmpty()
	{
		assertThrows(DomainException.class, () -> {
			
			toDoNote.setText("");
			
		});
	}
	
	@Test
	public void should_ThrowException_WhenToDoNoteCreationDateNotAssigned()
	{
		var info = createTestToDoNoteInfo();
		
		info.setCreatedAt(null);
		
		assertThrows(DomainException.class, () -> {
			
			new ToDoNote(info);
			
		});
	}
	
	private static ToDoNote createTestToDoNote()
	{
		var info = createTestToDoNoteInfo();
		
		return new ToDoNote(info);
	}
	
	private static ToDoNoteInfo createTestToDoNoteInfo()
	{
		var info = new ToDoNoteInfo();
		
		info.setId(UUID.randomUUID());
		info.setName("Note#1");
		info.setText("some note text");
		info.setCreatedAt(LocalDateTime.now());
		
		return info;
	}
}
