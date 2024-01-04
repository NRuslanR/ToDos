package org.examples.todos.domain.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.common.entities.DomainAggregateRootActorNotAssignedException;
import org.examples.todos.domain.common.entities.DomainAggregateWorkingRulesNotAssignedException;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.operations.todos.forming.StandardToDoFormer;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.examples.todos.domain.rules.todos.access.ToDoUserAccessRuleException;
import org.examples.todos.domain.rules.todos.performing.OverlappingToDoIsNotParentException;
import org.examples.todos.domain.rules.todos.performing.OverlappingToDoIsNotPerformedException;
import org.examples.todos.domain.rules.todos.performing.ToDoActorHasNotPerformingRightsException;
import org.examples.todos.domain.rules.todos.performing.ToDoIsAlreadyPerformedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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
	
	@Test
	public void should_ThrowException_WhenWokingRulesNotAssignedForActor()
	{
		var invalidToDo = ToDoTestUtils.createSimpleToDoWithDefaultMediumPriority("To-Do", actor);
		
		assertThrows(DomainAggregateWorkingRulesNotAssignedException.class, () -> {
			
			invalidToDo.setActor(actor);
			
		});
	}
	
	@Test
	public void should_ThrowException_When_ActorNotAssignedAtEditing()
	{
		var invalidToDoForEdit = ToDoTestUtils.createSimpleToDoWithDefaultLowPriority("To-Do", actor);
		
		assertThrows(DomainAggregateRootActorNotAssignedException.class, () -> {
			
			invalidToDoForEdit.setName("Failed attempt");
			
		});
	}
	
	@Test
	public void should_AssignName_When_ItIsNotNullAndWhitespaces()
	{
		final var name = "To-Do#1";
		
		testToDo.setName(name);
		
		assertEquals(name, testToDo.getName());
	}
	
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "", "   " })
	public void should_ThrowException_When_AssignableNameIsNullOrWhitespaces(String name)
	{
		assertThrows(ToDoNameIncorrectException.class, () -> {
			
			testToDo.setName(name);
			
		});
	}
	
	@Test
	public void should_AssignParentToDoId_When_ItIsNotNull()
	{
		final var parentToDoId = UUID.randomUUID();
		
		testToDo.setParentToDoId(parentToDoId);
		
		assertEquals(parentToDoId, testToDo.getParentToDoId());
	}
	
	@Test
	public void should_ThrowException_When_AssignableParentToDoIdIsNull()
	{
		assertThrows(ToDoParentIsNullException.class, () -> {
			
			testToDo.setParentToDoId(null);
			
		});
	}
	
	@Test
	public void should_ThrowException_When_AssignableParentToDoIsItSelf()
	{
		assertThrows(ToDoParentItSelfException.class, () -> {
			
			testToDo.setParentToDoId(testToDo.getId());		
		});
	}
	
	@Test
	public void should_AssignPriority_When_ItIsValid()
	{
		final var priority = ToDoPriority.Low(0);
		
		testToDo.setPriority(priority);
		
		assertEquals(priority, testToDo.getPriority());
	}
	
	@Test
	public void should_ThrowException_WhenAssignablePriorityInValid()
	{
		assertThrows(DomainException.class, () -> {
			
			testToDo.setPriority(null);
			
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "", " ", "description" })
	public void should_AssignDescription_WhenItIsAny(String description)
	{
		testToDo.setDescription(description);

		assertEquals(description, testToDo.getDescription());
	}
	
	@Test
	public void should_Perform_When_ToDoIsValid_And_ActorHasAccessRights()
	{
		testToDo.perform();

		assertTrue(testToDo.isPerformed());
		assertNotNull(testToDo.getPerformingDate());
	}
	
	@Test
	public void should_ThrowException_When_TryingToDoPerform_And_ItIsAlreadyPerformed()
	{
		testToDo.perform();
		
		assertThrows(ToDoIsAlreadyPerformedException.class, () -> {
		
			testToDo.perform();
		});
	}
	
	@Test
	public void should_ThrowException_When_TryingToDoPerform_And_ActorHasNotAccessRights()
	{
		var newActor = UserTestUtils.createSimpleUserWithClaims(3, 3, true, true);
		
		testToDo.setActor(newActor);
		
		assertThrows(ToDoActorHasNotPerformingRightsException.class, () -> {
			
			testToDo.perform();
			
		});
	}
	
	@ParameterizedTest
	@MethodSource("dateTimesForPerforming")
	public void should_PerformAtSpecifiedDateTime_When_ToDoIsValid_And_ActorHasAccessRights(
		LocalDateTime dateTime
	)
	{
		testToDo.performForDateTime(dateTime);
		
		assertTrue(testToDo.isPerformed());
		assertEquals(dateTime, testToDo.getPerformingDate());
	}
	
	private static Stream<Arguments> dateTimesForPerforming()
	{
		return Stream.of(
			Arguments.of(
				LocalDateTime.of(
					LocalDate.of(2056, Month.DECEMBER, 12), 
					LocalTime.of(12, 54)
				)
			),
			Arguments.of(
				LocalDateTime.of(
					LocalDate.of(2025, Month.FEBRUARY, 23), 
					LocalTime.of(23, 43)
				)
			)
		);
	}
	
	@Test
	public void should_PerformAsOverlapped_When_TargetToDoAndOverlappingToDoValid()
	{
		var overlappingToDo = createTestToDoForPerformingOverlapping();
		
		overlappingToDo.perform();
		
		testToDo.setParentToDoId(overlappingToDo.getId());
		testToDo.performByOverlapping(overlappingToDo);
		
		assertTrue(testToDo.isPerformed());
		assertEquals(overlappingToDo.getPerformingDate(), testToDo.getPerformingDate());
	}
	
	@Test
	public void should_ThrowException_When_TryingToDoPerform_And_OverlappingToDoIsNotPerformed()
	{
		var overlappingToDo = createTestToDoForPerformingOverlapping();	
		
		testToDo.setParentToDoId(overlappingToDo.getId());
		
		assertThrows(OverlappingToDoIsNotPerformedException.class, () -> {
			
			testToDo.performByOverlapping(overlappingToDo);
			
		});
	}

	@Test
	public void should_ThrowException_When_TryingToDoPerform_And_OverlappingToDoIsNotParent()
	{
		var overlappingToDo = createTestToDoForPerformingOverlapping();
		
		overlappingToDo.perform();
		
		assertThrows(OverlappingToDoIsNotParentException.class, () -> {
			
			testToDo.performByOverlapping(overlappingToDo);
			
		});
	}
	
	@Test
	public void should_ThrowException_When_TryingPerformAlreadyPerformedToDo_By_OverlappingToDo()
	{
		var overlappingToDo = createTestToDoForPerformingOverlapping();
		
		overlappingToDo.perform();
		
		testToDo.setParentToDoId(overlappingToDo.getId());
		testToDo.perform();
		
		assertThrows(ToDoIsAlreadyPerformedException.class, () -> {
			
			testToDo.performByOverlapping(overlappingToDo);
			
		});
	}
	
	private static ToDo createTestToDoForPerformingOverlapping() {
		
		var toDoActor = UserTestUtils.createSimpleUserWithResetClaims();
		var toDoInfo = ToDoTestUtils.createSimpleToDoInfo("To-Do#123", ToDoPriority.Low(0), toDoActor);
		
		return toDoFormer.formToDo(toDoInfo, toDoActor);
	}
	
	@Test
	public void should_ResetParentId_When_ToDoIsValid()
	{
		testToDo.setParentToDoId(UUID.randomUUID());
		
		testToDo.resetParentToDo();
		
		assertNull(testToDo.getParentToDoId());
	}
	
	@ParameterizedTest
	@MethodSource("actorsThatHasNotEditRights")
	public void should_ThrowException_When_TryingToDoEdit_And_ActorHasNotAccessRights(User invalidActor)
	{
		testToDo.setActor(invalidActor);
		
		assertThrowsToDoStateEditForbiddenByActorAccessRights();
	}

	private static Stream<Arguments> actorsThatHasNotEditRights()
	{
		return Stream.of(
			Arguments.of(UserTestUtils.createSimpleUserWithClaims(3, 2, false, true))
		);		
	}
	
	@Test
	public void should_ThrowException_When_TryingToDoEdit_And_ItIsPerformed()
	{
		testToDo.perform();
		
		assertThrowsToDoStateEditForbiddenByActorAccessRights();
	}
	
	private void assertThrowsToDoStateEditForbiddenByActorAccessRights() {
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.setName("few");
			
		});
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.setParentToDoId(UUID.randomUUID());
			
		});
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.setPriority(ToDoPriority.Low(0));
			
		});
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.setDescription("");
			
		});
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.addNote(ToDoNoteTestUtils.createSimpleToDoNote("note", "text"));
			
		});
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.changeNote(ToDoNoteTestUtils.createSimpleToDoNoteInfo("note", "text"));
			
		});
		
		assertThrows(ToDoUserAccessRuleException.class, () -> {
			
			testToDo.removeNote(UUID.randomUUID());
			
		});
	}
}
