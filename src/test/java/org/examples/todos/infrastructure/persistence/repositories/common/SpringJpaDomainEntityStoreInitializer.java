package org.examples.todos.infrastructure.persistence.repositories.common;

import java.util.Collection;

import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpringJpaDomainEntityStoreInitializer<
	Entity extends DomainEntity, EntityId,
	JpaEntity extends BaseEntity<EntityId>
> 
	extends DomainEntityStoreInitializer<Entity>
{
	private final JpaRepository<JpaEntity, EntityId> jpaRepository;
	private final DomainEntityInfoConverter domainEntityInfoConverter;
	
	@Override
	protected void doSeed(Collection<Entity> initialEntities) 
	{
		var jpaEntities = initialEntities.stream().map(e -> {
			
			return (JpaEntity)domainEntityInfoConverter.convert(e.getInfo());
			
		}).toList();

		jpaRepository.saveAllAndFlush(jpaEntities);
	}

	@Override
	public void clear() 
	{
		jpaRepository.deleteAll();
	}
}
