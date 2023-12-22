package org.examples.todos.domain.operations.todos.forming;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoPriorityType;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.domain.resources.users.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StandardToDoFormerTests 
{
	private User actor;
	private ToDoFormer toDoFormer;
	
	@BeforeEach
	public void setup()
	{
		actor = createTestToDoActor();
		
		toDoFormer = new StandardToDoFormer();
	}
	
	@ParameterizedTest
	@MethodSource("validToDoInfos")
	public void should_ReturnsValidToDo_When_InfoValid_And_ActorHasItViewAccess(ToDoInfo toDoInfo)
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
	}
	
	private static Stream<Arguments> validToDoInfos()
	{
		return Stream.of(
			Arguments.of(
				new ToDoInfo(
					UUID.randomUUID(), 
					"To-Do#1", 
					new ToDoPriority(ToDoPriorityType.Medium, 20),
					LocalDateTime.now(), 
					createTestToDoActor()
				)
			)
		);
	}
	
	private static User createTestToDoActor()
	{
		return 
			new User(
				new UserInfo(
					UUID.randomUUID(), 
					new UserName("first", "second", "third"), 
					new UserRole(
						new UserRoleInfo(
							UUID.randomUUID(), 
							"role", 
							new UserRoleClaims(0, 0, false, false)
						)
					)
				)
			);
	}
}
