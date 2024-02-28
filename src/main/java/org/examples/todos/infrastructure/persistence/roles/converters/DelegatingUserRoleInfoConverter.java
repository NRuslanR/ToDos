package org.examples.todos.infrastructure.persistence.roles.converters;

import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatingUserRoleInfoConverter implements UserRoleInfoConverter
{
	private final DomainEntityInfoConverter<UserRoleInfo, RoleEntity> internalConverter;

	@Override
	public RoleEntity convert(UserRoleInfo entityInfo) 
	{	
		return internalConverter.convert(entityInfo);
	}

	@Override
	public UserRoleInfo convert(RoleEntity other) 
	{	
		return internalConverter.convert(other);
	}

}
