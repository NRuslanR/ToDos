package org.examples.todos.domain.resources.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class UserNameTests 
{
	@Test
	public void should_Be_Created_CorrectUserName_When_All_NamePartsNotEmpty()
	{
		var userName = new UserName("firstName", "middleName", "lastName");
		
		assertIterableEquals(
			Arrays.asList("firstName", "middleName", "lastName"), 
			Arrays.asList(userName.firstName(), userName.middleName(), userName.lastName())
		);
	}
	
	@ParameterizedTest
	@MethodSource("userNameInputs")
	public void should_ThrowException_When_AnyNamePartEmpty(
		String firstName, String middleName, String lastName
	)
	{
		assertThrows(DomainException.class, () -> {
			
			new UserName(firstName, middleName, lastName);
			
		});
	}
	
	private static Stream<Arguments> userNameInputs()
	{
		return Stream.of(
			Arguments.of("", "a", "b"),
			Arguments.of("a", "", "b"),
			Arguments.of("a", "b", ""),
			Arguments.of(null, "a", "b"),
			Arguments.of("a", null, "b"),
			Arguments.of("a", "b", null)
		);
	}
	
	@Test
	public void should_Be_Changed_AnyUserNamePart_When_AssignableValueNotEmpty()
	{
		var userName = new UserName("a", "b", "c");
		
		userName = userName.changeFirstName("aa");
		
		assertEquals("aa", userName.firstName());

		userName = userName.changeMiddleName("bb");
		
		assertEquals("bb", userName.middleName());
		
		userName = userName.changeLastName("cc");
		
		assertEquals("cc", userName.lastName());
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "firstName", "middleName", "lastName" })
	public void should_ThrowExcepton_When_Changing_AnyUserNamePart_by_EmptyValue(String namePart)
	{
		var userName = new UserName("a", "b", "c");
		
		assertThrows(DomainException.class, () -> {
			
			if (namePart.equals("firstName"))
				userName.changeFirstName("");
			
			else if (namePart.equals("middleName"))
				userName.changeMiddleName("");
			
			else userName.changeLastName("");
			
		});
	}
}
