package org.examples.todos.infrastructure.persistence.todos.repositories;

import java.util.UUID;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.common.entities.formers.DomainEntityFormer;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.repositories.SpringJpaDomainEntityRepository;
import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;

public class SpringJpaToDoRepository 
	extends SpringJpaDomainEntityRepository<ToDo, ToDoInfo, UUID, ToDoEntity>
	implements ToDoRepository
{

	public SpringJpaToDoRepository(
		SpringJpaToDoEntityRepository jpaRepository,
		DomainEntityInfoConverter<ToDoInfo, ToDoEntity> entityInfoConverter,
		DomainEntityFormer<ToDo, ToDoInfo> entityFormer) 
	{
		super(jpaRepository, entityInfoConverter, entityFormer);
	}

}
