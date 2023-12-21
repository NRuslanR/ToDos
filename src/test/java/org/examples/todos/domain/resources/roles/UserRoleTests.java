package org.examples.todos.domain.resources.roles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import org.examples.todos.domain.common.errors.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//refactor: remove shouldBe test add more fine-grained tests per property
public class UserRoleTests 
{
	@ParameterizedTest
	@MethodSource("validUserRoleInfos")
	public void should_Be_Created_CorrectUserRole_When_InfoValid(UserRoleInfo userRoleInfo)
	{
		var userRole = new UserRole(userRoleInfo);
		
		assertEquals(userRoleInfo.getId(), userRole.getId());
		assertEquals(userRoleInfo.getName(), userRole.getName());
		assertEquals(userRoleInfo.getClaims(), userRole.getClaims());
	
		if (!Objects.isNull(userRoleInfo.getDescription()))
			assertEquals(userRoleInfo.getDescription().getValue(), userRole.getDescription());
	}
	
	private static Stream<Arguments> validUserRoleInfos()
	{
		var roleInfoList = new ArrayList<UserRoleInfo>();
		
		var claims = new UserRoleClaims(0, 0, false, false);
		
		roleInfoList.add(new UserRoleInfo(UUID.randomUUID(), "TestRoleA", claims));	
		roleInfoList.add(new UserRoleInfo(UUID.randomUUID(), "TestRoleB", null, claims));	
		roleInfoList.add(new UserRoleInfo(UUID.randomUUID(), "TestRoleC", "TestRoleC", claims));
		
		return roleInfoList.stream().map(Arguments::of);
	}
	
	@Test
	public void should_ThrowException_When_UserRoleIdentityNotAssigned()
	{
		var roleInfo = new UserRoleInfo(null, "TestRole", new UserRoleClaims(0, 0, false, false));
		
		assertThrows(DomainException.class, () -> {
			
			new UserRole(roleInfo);
			
		});
	}
	
	@Test
	public void should_ThrowException_When_UserRoleNameNotAssigned()
	{
		var roleInfo = 
			new UserRoleInfo(
				UUID.randomUUID(), null, new UserRoleClaims(0, 0, false, false) 
			);
		
		assertThrows(DomainException.class, () -> {
			
			new UserRole(roleInfo);
		});
	}
	
	@Test
	public void should_ThrowException_When_UserRoleClaimsNotAssigned()
	{
		var roleInfo = new UserRoleInfo(UUID.randomUUID(), "TestRole", null);
		
		assertThrows(DomainException.class, () -> {
			
			new UserRole(roleInfo);
		});
	}
}
