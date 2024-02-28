package org.examples.todos.infrastructure.persistence.roles.converters;

import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.infrastructure.persistence.common.converters.ModelMapperDomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;
import org.modelmapper.ModelMapper;

public class ModelMapperUserRoleInfoConverter 
	extends ModelMapperDomainEntityInfoConverter<UserRoleInfo, RoleEntity> 
{
	public ModelMapperUserRoleInfoConverter(ModelMapper mapper) 
	{
		super(mapper);
	}

	@Override
	protected Class<RoleEntity> getSourceType() 
	{
		return RoleEntity.class;
	}

	@Override
	protected Class<UserRoleInfo> getEntityInfoType() 
	{
		return UserRoleInfo.class;
	}

}
