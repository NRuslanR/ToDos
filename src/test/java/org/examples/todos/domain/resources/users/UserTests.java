package org.examples.todos.domain.resources.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTests 
{
	private User user;
	
	@BeforeEach
	public void setup()
	{	
		user = createTestUser();	
	}
	
	@ParameterizedTest
	@MethodSource("validUserInfos")
	public void should_Be_Created_CorrectUser_When_InfoValid(UserInfo userInfo)
	{
		var user = new User(userInfo);
		
		assertIterableEquals(
			Arrays.asList(userInfo.getId(), userInfo.getName(), userInfo.getRole()), 
			Arrays.asList(user.getId(), user.getName(), user.getRole())
		);
		
		if (!Objects.isNull(userInfo.getAddress()))
			assertEquals(userInfo.getAddress().getValue(), user.getAddress());
	}
	
	private static Stream<Arguments> validUserInfos()
	{
		var userInfos = new ArrayList<UserInfo>();
		
		userInfos.add(createTestUserInfo());
		
		return userInfos.stream().map(Arguments::of);
	}
	
	@Test
	public void should_Be_CorrectUser_When_Assigned_ValidName()
	{
		var changedNamePart = "newFirstName";
		
		user.setFirstName(changedNamePart);
		
		assertEquals(changedNamePart, user.getFirstName());
		
		changedNamePart = "newMiddleName";
		
		user.setMiddleName(changedNamePart);
		
		assertEquals(changedNamePart, user.getMiddleName());
		
		changedNamePart = "newLastName";
		
		user.setLastName(changedNamePart);
		
		assertEquals(changedNamePart, user.getLastName());
	}
	
	@Test
	public void should_Be_CorrectUser_When_Assigned_ValidAddress()
	{
		user.setAddress(new UserAddress("a", "b", "c"));
		
		assertEquals(new UserAddress("a", "b", "c"), user.getAddress());
	}
	
	@Test
	public void should_Be_CorrectUser_When_Assigned_ValidRole()
	{
		var newRoleInfo = new UserRoleInfo();
		
		newRoleInfo.setId(UUID.randomUUID());
		newRoleInfo.setName("a");
		newRoleInfo.setClaims(new UserRoleClaims(0, 0, false, false));
		
		var role = new UserRole(newRoleInfo);
		
		user.setRole(role);
		
		assertEquals(role, user.getRole());
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "firstName", "middleName", "lastName" })
	public void should_ThrowException_When_UserNameNotCorrect(String arg)
	{
		assertThrows(
			DomainException.class, 
			() -> {
				if (arg.equals("firstName"))
					user.setFirstName("");
				
				else if (arg.equals("middleName"))
					user.setMiddleName(null);
				
				else user.setLastName("");
			}
		);
	}
	
	@Test
	public void should_ThrowException_When_UserAddressNotCorrect()
	{
		assertThrows(DomainException.class, () -> {
			
			user.setAddress(null);
			
		});
	}
	
	@Test
	public void should_ThrowException_When_UserRoleNotCorrect()
	{
		assertThrows(DomainException.class, () -> {
			
			user.setRole(null);
			
		});
	}
	
	private static User createTestUser()
	{
		return UserTestUtils.createSimpleUserWithResetClaims();
	}
	
	private static UserInfo createTestUserInfo()
	{
		return UserTestUtils.createSimpleUserInfoWithResetClaims();
	}
}
