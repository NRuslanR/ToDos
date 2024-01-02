package org.examples.todos.domain.operations.todos.forming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.actors.ToDoNote;
import org.examples.todos.domain.actors.ToDoNoteInfo;
import org.examples.todos.domain.actors.ToDoNoteList;
import org.examples.todos.domain.actors.ToDoNoteTestUtils;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoTestUtils;
import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.decisionsupport.search.doubles.StubToDoFinder;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.domain.resources.users.UserName;
import org.examples.todos.domain.rules.todos.access.ToDoNoteCreationCountLimitReachedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StandardToDoCreationServiceTests 
{
	private static User actor;
	
	@BeforeAll
	public static void setup()
	{
		actor = createTestToDoActor();
	}
	
	@ParameterizedTest
	@MethodSource("validToDoInfos")
	public void should_Be_Created_NewToDo_When_InitialInfoValid_And_ActorHasAccessRights(
		ToDoInfo toDoInfo
	)
	{
		var actorToDoList = createTestToDoList(actor);
		var toDoCreationService = createStandardToDoCreationService(actorToDoList);
		
		var toDo = toDoCreationService.createToDoFor(toDoInfo, actor);
		
		assertNotNull(toDo.getId());
		assertNotEquals(toDoInfo.getId(), toDo.getId());
		assertNull(toDo.getPerformingDate());
		assertNotNull(toDo.getCreationDate());
		assertNotEquals(toDoInfo.getCreationDate(), toDo.getCreationDate());
		assertNull(toDo.getParentToDoId());
		
		if (!Objects.isNull(toDoInfo.getNotes()))
			assertEquals(toDoInfo.getNotes().getValue(), toDo.getNotes());
	}
	
	@Test
	public void should_ThrowException_When_ToDoCreationCountLimit_Is_Reached()
	{
		var actorToDoList = createTestToDoList(actor);
		
		actorToDoList.add(
			ToDoTestUtils.createSimpleToDo(
				"To-Do#New1", ToDoPriority.Urgent(0), actor
			)
		);
		
		var toDoCreationService = createStandardToDoCreationService(actorToDoList);
		
		assertThrows(
			ToDoCreationCountLimitReachedException.class, 
			() -> {
			
				toDoCreationService.createToDoFor(
					ToDoTestUtils.createSimpleToDoInfo(
						"To-Do#New2", ToDoPriority.Low(0), actor
					) , 
					actor
				);
			}
		);
	}
	
	@Test
	public void should_ThrowException_When_ToDoNoteCreationCountLimit_Is_Reached()
	{
		var toDoNoteList = createTestToDoNoteList();
		
		toDoNoteList.add(ToDoNoteTestUtils.createSimpleToDoNote("note#3", "text"));
		
		var toDoInfo = ToDoTestUtils.createSimpleToDoInfo("To-Do#New", ToDoPriority.Low(0), actor);
		
		toDoInfo.setNotes(Intention.of(toDoNoteList));
		
		var actorToDoList = createTestToDoList(actor);
		var toDoCreationService = createStandardToDoCreationService(actorToDoList);
		
		assertThrows(ToDoNoteCreationCountLimitReachedException.class, () -> {
			
			toDoCreationService.createToDoFor(toDoInfo, actor);
			
		});
	}
	
	private static Stream<Arguments> validToDoInfos()
	{
		return Stream.of(
			Arguments.of(
				ToDoTestUtils.createSimpleToDoInfo("To-Do#New1", ToDoPriority.Low(13), actor)
			),
			Arguments.of(
				new ToDoInfo(
					null, 
					"To-Do#New2", 
					ToDoPriority.Medium(8), 
					null, 
					actor, 
					Intention.of(UUID.randomUUID()), 
					Intention.of("description"),
					Intention.of(LocalDateTime.now()), 
					null
				)
			),
			Arguments.of(
				new ToDoInfo(
					null, 
					"To-Do#New3", 
					ToDoPriority.Urgent(5), 
					null, 
					actor, 
					Intention.of(UUID.randomUUID()), 
					Intention.of("description"),
					Intention.of(LocalDateTime.now()), 
					Intention.of(createTestToDoNoteList())
				)
			)
		);
	}
	
	private static User createTestToDoActor() {
		
		return new User(
			new UserInfo(
				UUID.randomUUID(), 
				new UserName("a", "b", "c"), 
				new UserRole(
					new UserRoleInfo(
						UUID.randomUUID(), 
						"a", 
						new UserRoleClaims(3, 2, false, false)
					)
				)
			)
		);
	}

	private static ToDoList createTestToDoList(User author) {
		
		return new ToDoList(
			Arrays.asList(
				ToDoTestUtils.createSimpleToDo("To-Do#1", ToDoPriority.Urgent(14), author),
				ToDoTestUtils.createSimpleToDo("To-Do#2", ToDoPriority.Medium(1), author)
			)
		);
	}

	private static ToDoNoteList createTestToDoNoteList()
	{
		return new ToDoNoteList(
			Arrays.asList(
				ToDoNoteTestUtils.createSimpleToDoNote("note#1", "text"),
				ToDoNoteTestUtils.createSimpleToDoNote("note#2", "text")
			)
		);
	}
	
	private static ToDoCreationService createStandardToDoCreationService(ToDoList toDoList)
	{
		var toDoFinder = new StubToDoFinder(toDoList);
		var toDoFormer = new StandardToDoFormer();
		
		return new StandardToDoCreationService(toDoFinder, toDoFormer);
	}
}
