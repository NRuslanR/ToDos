package org.examples.todos.domain.common.entities.rules.access;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;

public interface DomainAggregateRemovingRule<
	Aggregate extends DomainAggregateRoot,
	Actor extends DomainEntity
	
> extends DomainEntityRelationshipRule<Aggregate, Actor>
{
	default boolean canRemovedByActor(Aggregate toDo, Actor user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureCanRemovedByActor(Aggregate toDo, Actor user)
	{
		ensureSatisfiedFor(toDo, user);
	}
}
