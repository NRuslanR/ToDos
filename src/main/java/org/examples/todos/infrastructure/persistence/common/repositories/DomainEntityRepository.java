package org.examples.todos.infrastructure.persistence.common.repositories;

import org.examples.todos.domain.common.entities.DomainEntity;

public interface DomainEntityRepository<
	Entity extends DomainEntity, 
	Identity
> 
{
	Iterable<Entity> findAll();
	Entity findById(Identity id);
	void save(Entity entity);
	void saveAll(Iterable<Entity> entities);
	void remove(Entity entity);
	void removeAll(Iterable<Entity> entities);
}
