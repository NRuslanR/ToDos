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

public class UserAddressTests 
{
	@Test
	public void should_Be_Created_CorrectUserAddress_When_All_PartsNotEmpty()
	{
		var UserAddress = new UserAddress("street", "house", "room");
		
		assertIterableEquals(
			Arrays.asList("street", "house", "room"), 
			Arrays.asList(UserAddress.street(), UserAddress.house(), UserAddress.room())
		);
	}
	
	@ParameterizedTest
	@MethodSource("userAddressInputs")
	public void should_ThrowException_When_AnyUserAddressPartEmpty(
		String street, String house, String room
	)
	{
		assertThrows(DomainException.class, () -> {
			
			new UserAddress(street, house, room);
			
		});
	}
	
	private static Stream<Arguments> userAddressInputs()
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
	public void should_Be_Changed_AnyUserAddressPart_When_AssignableValueNotEmpty()
	{
		var UserAddress = new UserAddress("a", "b", "c");
		
		UserAddress = UserAddress.changeStreet("aa");
		
		assertEquals("aa", UserAddress.street());

		UserAddress = UserAddress.changeHouse("bb");
		
		assertEquals("bb", UserAddress.house());
		
		UserAddress = UserAddress.changeRoom("cc");
		
		assertEquals("cc", UserAddress.room());
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "street", "house", "room" })
	public void should_ThrowExcepton_When_Changing_AnyUserAddressPart_by_EmptyValue(String namePart)
	{
		var UserAddress = new UserAddress("a", "b", "c");
		
		assertThrows(DomainException.class, () -> {
			
			if (namePart.equals("street"))
				UserAddress.changeStreet("");
			
			else if (namePart.equals("house"))
				UserAddress.changeHouse("");
			
			else UserAddress.changeRoom("");
			
		});
	}
}