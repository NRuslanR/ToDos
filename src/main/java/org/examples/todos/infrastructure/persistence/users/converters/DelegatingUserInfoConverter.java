package org.examples.todos.infrastructure.persistence.users.converters;

import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatingUserInfoConverter implements UserInfoConverter 
{
	private final DomainEntityInfoConverter<UserInfo, UserEntity> internalConverter;

	@Override
	public UserEntity convert(UserInfo entityInfo) 
	{
		return internalConverter.convert(entityInfo);
	}

	@Override
	public UserInfo convert(UserEntity other) 
	{
		return internalConverter.convert(other);
	}

}
