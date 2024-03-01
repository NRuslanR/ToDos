package org.examples.todos.infrastructure.persistence.repositories.roles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.infrastructure.persistence.repositories.common.DomainEntityRepositoryTests;
import org.examples.todos.infrastructure.persistence.roles.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleRepositoryTests extends DomainEntityRepositoryTests 
{
	@Autowired
	public UserRoleRepositoryTests(
		UserRoleRepository userRoleRepository, 
		UserRoleStoreInitializer userRoleStoreInitializer
	)
	{
		super(userRoleRepository, userRoleStoreInitializer);
	}
	
	@Override
	protected List<DomainEntity> createSeedEntities() {
		
		return Arrays.asList(
			createTestUserRole("RoleA"),
			createTestUserRole("RoleB"),
			createTestUserRole("RoleC")
		);
	}
	
	@Override
	protected DomainEntity createDomainEntityToAdd() {
		
		return createTestUserRole("TestRole");
	}

	private UserRole createTestUserRole(String name)
	{
		return new UserRole(
			new UserRoleInfo(
				UUID.randomUUID(),
				name, 
				new UserRoleClaims(3, 2, false, true)
			)
		);
	}
	
	@Override
	protected void changeDomainEntityToUpdate(DomainEntity entity) 
	{
		var userRole = (UserRole)entity;
		
		userRole.setName("New Role Name");
	}

	@Override
	protected void assertDomainEntitiesEquals(DomainEntity expected, DomainEntity actual) {
		
		var expectedRole = (UserRole)expected;
		var actualRole = (UserRole)actual;
		
		assertEquals(expectedRole.getId(), actualRole.getId());
		assertEquals(expectedRole.getName(), actualRole.getName());
		assertEquals(expectedRole.getDescription(), actualRole.getDescription());
		assertEquals(expectedRole.getClaims(), actualRole.getClaims());
	}
}
