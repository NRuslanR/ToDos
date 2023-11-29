package org.examples.todos.domain.common.entities.rules.access;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.rules.DomainAggregateWorkingRules;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;

public interface DomainAggregateViewingRule<
	Aggregate extends DomainAggregateRoot,
	Actor extends DomainEntity
	
> extends DomainEntityRelationshipRule<Aggregate, Actor>
{
	default boolean canViewedByActor(Aggregate toDo, Actor user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureCanViewedByActor(Aggregate toDo, Actor user)
	{
		ensureSatisfiedFor(toDo, user);
	}
}
