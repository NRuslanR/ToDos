package org.examples.todos.domain.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ToDoPriorityTests 
{
	@ParameterizedTest
	@MethodSource("validToDoPriorityInputs")
	public void should_Be_Created_CorrectToDoPriority_When_InputValid(
		ToDoPriorityType type, float value
	)
	{
		var priority = new ToDoPriority(type, value);
		
		assertEquals(type, priority.type());
		assertEquals(value, priority.value());
	}
	
	private static Stream<Arguments> validToDoPriorityInputs()
	{
		return Stream.of(
			Arguments.of(ToDoPriorityType.Low, 0),
			Arguments.of(ToDoPriorityType.Low, 14),
			Arguments.of(ToDoPriorityType.Medium, 0),
			Arguments.of(ToDoPriorityType.Medium, 19),
			Arguments.of(ToDoPriorityType.Urgent, 0),
			Arguments.of(ToDoPriorityType.Urgent, 42)
		);
	}
	
	@Test
	public void should_Be_Changed_ToDoPriorityValue_When_AssignableValueNonNegative()
	{
		var priority = new ToDoPriority(ToDoPriorityType.Medium, 0);
		
		final var newPriorityValue = 10;
		
		priority = priority.changeValue(newPriorityValue);
		
		assertEquals(newPriorityValue, priority.value());
	}
	
	@Test
	public void should_ThrowException_When_ToDoPriorityValueNegative()
	{
		var priority = new ToDoPriority(ToDoPriorityType.Urgent, 13);
		
		assertThrows(DomainException.class, () -> {
			
			var newPriority = priority.changeValue(-1);
			
		});
	}
}
