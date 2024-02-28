package org.examples.todos.infrastructure.persistence.repositories.roles;

import java.util.Collection;

import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.infrastructure.persistence.repositories.common.DomainEntityStoreInitializer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatingUserRoleStoreInitializer extends UserRoleStoreInitializer 
{
	private final DomainEntityStoreInitializer<UserRole> internalInitializer;
	
	@Override
	protected void doSeed(Collection<UserRole> initialEntities) 
	{
		internalInitializer.seed(initialEntities);
	}

	@Override
	public void clear() 
	{
		internalInitializer.clear();
	}
}
