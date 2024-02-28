package org.examples.todos.infrastructure.persistence.todos.converters;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.infrastructure.persistence.common.converters.ModelMapperDomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;
import org.modelmapper.ModelMapper;

public class ModelMapperToDoInfoConverter 
	extends ModelMapperDomainEntityInfoConverter<ToDoInfo, ToDoEntity> 
{

	public ModelMapperToDoInfoConverter(ModelMapper mapper) 
	{
		super(mapper);
	}

	@Override
	protected Class<ToDoEntity> getSourceType() 
	{
		return ToDoEntity.class;
	}

	@Override
	protected Class<ToDoInfo> getEntityInfoType() 
	{
		return ToDoInfo.class;
	}

}
