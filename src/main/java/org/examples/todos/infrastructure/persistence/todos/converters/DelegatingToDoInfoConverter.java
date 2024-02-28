package org.examples.todos.infrastructure.persistence.todos.converters;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatingToDoInfoConverter implements ToDoInfoConverter 
{
	private final DomainEntityInfoConverter<ToDoInfo, ToDoEntity> internalConverter;
	
	@Override
	public ToDoEntity convert(ToDoInfo entityInfo) 
	{
		return internalConverter.convert(entityInfo);
	}

	@Override
	public ToDoInfo convert(ToDoEntity other) 
	{
		return internalConverter.convert(other);
	}

}
