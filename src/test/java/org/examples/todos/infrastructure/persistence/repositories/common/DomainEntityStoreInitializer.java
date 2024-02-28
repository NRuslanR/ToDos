package org.examples.todos.infrastructure.persistence.repositories.common;

import java.util.Collection;

import org.examples.todos.domain.common.entities.DomainEntity;

public abstract class DomainEntityStoreInitializer<Entity extends DomainEntity> 
{
	private Collection<Entity> entities;
	
	public void seed(Collection<Entity> initialEntities)
	{
		doSeed(initialEntities);
		
		entities = initialEntities;
	}

	protected abstract void doSeed(Collection<Entity> initialEntities);

	public Collection<Entity> getEntities()
	{
		return entities;
	}

	public abstract void clear();
}
