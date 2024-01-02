package org.examples.todos.domain.operations.todos.forming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoNote;
import org.examples.todos.domain.actors.ToDoNoteInfo;
import org.examples.todos.domain.actors.ToDoNoteList;
import org.examples.todos.domain.actors.ToDoNoteTestUtils;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoPriorityType;
import org.examples.todos.domain.actors.ToDoTestUtils;
import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.domain.resources.users.UserName;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StandardToDoFormerTests 
{
	private static ToDoFormer toDoFormer;
	
	@BeforeAll
	public static void setup()
	{
		toDoFormer = new StandardToDoFormer();
	}
	
	@ParameterizedTest
	@MethodSource("validToDoInput")
	public void should_Be_Created_ValidToDo_When_InfoValid_And_ActorHasItViewAccess(
		ToDoInfo toDoInfo, User actor
	)
	{		
		var formedToDo = toDoFormer.formToDo(toDoInfo, actor);

		assertIterableEquals(
			Arrays.asList(
				toDoInfo.getAuthor(),
				toDoInfo.getCreationDate(),
				toDoInfo.getId(),
				toDoInfo.getName(),
				toDoInfo.getPriority()
			),
			Arrays.asList(
				formedToDo.getAuthor(),
				formedToDo.getCreationDate(),
				formedToDo.getId(),
				formedToDo.getName(),
				formedToDo.getPriority()
			)
		);
		
		if (!Objects.isNull(toDoInfo.getDescription()))
			assertEquals(toDoInfo.getDescription().getValueOrDefault(""), formedToDo.getDescription());
		
		if (!Objects.isNull(toDoInfo.getParentToDoId()))
			assertEquals(toDoInfo.getParentToDoId().getValue(), formedToDo.getParentToDoId());
		
		if (!Objects.isNull(toDoInfo.getPerformingDate()))
			assertEquals(toDoInfo.getPerformingDate().getValue(), formedToDo.getPerformingDate());
		
		if (!Objects.isNull(toDoInfo.getNotes()))
			assertEquals(toDoInfo.getNotes().getValue(), formedToDo.getNotes());
	}
	
	private static Stream<Arguments> validToDoInput()
	{
		var authorActor = createTestToDoActor();
		var editForeignTasksActor = createTestToDoActorWhoCanEditForeignTasks();
		var removeForeignTasksActor = createTestToDoActorWhoCanRemoveForeignTasks();
		
		return Stream.of(
			Arguments.of(
				ToDoTestUtils.createSimpleToDoInfo(
					"To-Do#1", ToDoPriority.Medium(20), authorActor
				),
				authorActor.clone()
			),
			Arguments.of(
				new ToDoInfo(
					UUID.randomUUID(), 
					"To-Do#2", 
					ToDoPriority.Low(0), 
					LocalDateTime.now(), 
					createTestToDoActor(), 
					Intention.of(UUID.randomUUID()), 
					Intention.of("description"),
					Intention.of(LocalDateTime.now()), 
					Intention.of(
						new ToDoNoteList(
							Arrays.asList(
								ToDoNoteTestUtils.createSimpleToDoNote("Note#1", "text")
							)
						)
					)
				),
				editForeignTasksActor
			),
			Arguments.of(
				new ToDoInfo(
					UUID.randomUUID(), 
					"To-Do#3", 
					ToDoPriority.Urgent(13),
					LocalDateTime.now(), 
					createTestToDoActor(),
					Intention.of(UUID.randomUUID()),
					Intention.of(null),
					Intention.of(LocalDateTime.now()),
					Intention.of(new ToDoNoteList())
				),
				removeForeignTasksActor
			)
		);
	}
	
	@ParameterizedTest
	@MethodSource("inValidToDoInput")
	public void should_ThrowException_WhenToDoInfoInValid_Or_ActorHasNotItViewAccess(
		ToDoInfo toDoInfo, User actor
	)
	{
		assertThrows(DomainException.class, () -> {
			
			toDoFormer.formToDo(toDoInfo, actor);
			
		});
	}
	
	private static Stream<Arguments> inValidToDoInput()
	{
		var argumentsList = new ArrayList<Arguments>();
		
		var authorActor = createTestToDoActor();
		
		var inValidToDoInfo = new ToDoInfo();
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = new ToDoInfo();
		
		inValidToDoInfo.setId(UUID.randomUUID());
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setAuthor(authorActor);
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setCreationDate(LocalDateTime.now());
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setName("To-Do#1");
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setPriority(ToDoPriority.Low(0));
		inValidToDoInfo.setNotes(Intention.of(null));
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setNotes(null);
		inValidToDoInfo.setParentToDoId(Intention.of(null));
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setParentToDoId(null);
		inValidToDoInfo.setPerformingDate(Intention.of(null));
		
		argumentsList.add(Arguments.of(inValidToDoInfo, authorActor));
		
		inValidToDoInfo = inValidToDoInfo.clone();
		
		inValidToDoInfo.setPerformingDate(null);
		
		var inValidActor = createTestToDoActor();
		
		argumentsList.add(Arguments.of(inValidToDoInfo, inValidActor));
		
		return argumentsList.stream();
	}
	
	private static User createTestToDoActorWhoCanEditForeignTasks()
	{
		var actor = createTestToDoActor();
		
		var role = actor.getRole();
		
		role.setClaims(new UserRoleClaims(0, 0, true, false));
		
		actor.setRole(role);
		
		return actor;
	}
	
	private static User createTestToDoActorWhoCanRemoveForeignTasks()
	{
		var actor = createTestToDoActor();
		
		var role = actor.getRole();
		
		role.setClaims(new UserRoleClaims(0, 0, false, true));
		
		actor.setRole(role);
		
		return actor;
	}
	
	private static User createTestToDoActor()
	{
		return UserTestUtils.createSimpleUserWithResetClaims();
	}
}
