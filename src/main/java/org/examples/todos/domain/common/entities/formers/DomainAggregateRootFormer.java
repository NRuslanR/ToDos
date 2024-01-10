package org.examples.todos.domain.common.entities.formers;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.rules.DomainAggregateWorkingRules;
import org.examples.todos.domain.common.errors.DomainException;

public interface DomainAggregateRootFormer<	

	AggregateRoot extends DomainAggregateRoot,
	AggregateRootInfo extends DomainEntityInfo,
	AggregateWorkingRules extends DomainAggregateWorkingRules,
	Actor extends DomainEntity
	
> extends DomainEntityFormer<AggregateRoot, AggregateRootInfo>
{
	AggregateRoot formAggregateRoot(AggregateRootInfo info, Actor actor) throws DomainException;
}
