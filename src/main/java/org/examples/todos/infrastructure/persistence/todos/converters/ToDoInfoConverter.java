package org.examples.todos.infrastructure.persistence.todos.converters;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;

public interface ToDoInfoConverter extends DomainEntityInfoConverter<ToDoInfo, ToDoEntity> 
{

}
