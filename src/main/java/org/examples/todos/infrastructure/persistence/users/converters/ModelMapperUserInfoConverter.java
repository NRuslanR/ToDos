package org.examples.todos.infrastructure.persistence.users.converters;

import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.infrastructure.persistence.common.converters.ModelMapperDomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;
import org.modelmapper.ModelMapper;

public class ModelMapperUserInfoConverter 
	extends ModelMapperDomainEntityInfoConverter<UserInfo, UserEntity> 
{

	public ModelMapperUserInfoConverter(ModelMapper mapper) 
	{
		super(mapper);
	}

	@Override
	protected Class<UserEntity> getSourceType() 
	{
		return UserEntity.class;
	}

	@Override
	protected Class<UserInfo> getEntityInfoType() 
	{
		return UserInfo.class;
	}
}
