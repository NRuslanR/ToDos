package org.examples.todos.domain.resources.roles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UserRoleClaimsTests 
{
	@ParameterizedTest
	@MethodSource("validUserRoleClaimsInput")
	public void should_Be_Created_CorrectUserRoleClaims_When_AllClaimsValid(
		int allowedToDoCreationCount, 
		int allowedToDoNoteCreationCount,
		boolean canEditForeignToDos,
		boolean canRemoveForeignToDos)
	{
		var userRoleClaims =
			new UserRoleClaims(
				allowedToDoCreationCount, 
				allowedToDoNoteCreationCount, 
				canEditForeignToDos, 
				canRemoveForeignToDos
			);
		
		assertEquals(allowedToDoCreationCount, userRoleClaims.allowedToDoCreationCount());
		assertEquals(allowedToDoNoteCreationCount, userRoleClaims.allowedToDoNoteCreationCount());
		assertEquals(canEditForeignToDos, userRoleClaims.canEditForeignToDos());
		assertEquals(canRemoveForeignToDos, userRoleClaims.canRemoveForeignToDos());
	}
	
	private static Stream<Arguments> validUserRoleClaimsInput()
	{
		return Stream.of(
			Arguments.of(0, 0, false, false),
			Arguments.of(0, 1, false, true),
			Arguments.of(1, 0, true, false),
			Arguments.of(1, 1, true, true),
			Arguments.of(100, 100, true, true)
		);
	}
	
	@Test
	public void should_ThrowException_When_AllowedToDoCreationCountNegative()
	{
		assertThrows(DomainException.class, () -> {
		
			new UserRoleClaims(-1, 0, false, false);
			
		});
	}
	
	@Test
	public void should_ThrowException_When_AllowedToDoNoteCreationCountNegative()
	{
		assertThrows(DomainException.class, () -> {
			
			new UserRoleClaims(0, -1, false, false);
			
		});
	}
}
