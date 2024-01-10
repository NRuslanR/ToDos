package org.examples.todos.domain.common.entities.formers;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.rules.DomainAggregateWorkingRules;
import org.examples.todos.domain.common.errors.DomainException;

public abstract class BaseDomainAggregateRootFormer<

	AggregateRoot extends DomainAggregateRoot,
	AggregateRootInfo extends DomainEntityInfo,
	AggregateWorkingRules extends DomainAggregateWorkingRules,
	Actor extends DomainEntity
	
> implements DomainAggregateRootFormer<
	
	AggregateRoot, 
	AggregateRootInfo, 
	AggregateWorkingRules, 
	Actor

>
{
	public AggregateRoot formAggregateRoot(AggregateRootInfo info, Actor actor) throws DomainException
	{
		var aggregateRoot = formEntity(info);
		
		aggregateRoot.setWorkingRules(getWorkingRules());
		aggregateRoot.setActor(actor);
		
		return aggregateRoot;
	}

	protected abstract AggregateWorkingRules getWorkingRules();
}
