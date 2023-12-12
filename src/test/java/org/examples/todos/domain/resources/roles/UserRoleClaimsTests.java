package org.examples.todos.domain.resources.roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UserRoleClaimsTests 
{
	@ParameterizedTest
	@MethodSource("validUserRoleClaimsInput")
	public void shouldBeCorrectUserRoleClaimsIfInputValid(
		int allowedToDoCreationCount, 
		int allowedToDoNoteCreationCount,
		Boolean canEditForeignToDos,
		boolean canRemoveForeignToDos)
	{
		assertDoesNotThrow(() -> {;
		
			new UserRoleClaims(
				allowedToDoCreationCount, 
				allowedToDoNoteCreationCount, 
				canRemoveForeignToDos, 
				canRemoveForeignToDos
			);
		});
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
	public void shouldRaiseErrorIfAllowedToDoCreationCountNegative()
	{
		assertThrows(DomainException.class, () -> {
		
			new UserRoleClaims(-1, 0, false, false);
			
		});
	}
	
	@Test
	public void shouldRaiseErrorIfAllowedToDoNoteCreationCountNegative()
	{
		assertThrows(DomainException.class, () -> {
			
			new UserRoleClaims(0, -1, false, false);
			
		});
	}
}
