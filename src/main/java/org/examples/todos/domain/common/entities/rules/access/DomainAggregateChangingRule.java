package org.examples.todos.domain.common.entities.rules.access;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public interface DomainAggregateChangingRule<
	Aggregate extends DomainAggregateRoot,
	Actor extends DomainEntity
	
> extends DomainEntityRelationshipRule<Aggregate, Actor>
{
	default boolean canChangedByActor(Aggregate toDo, Actor user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureCanChangedByActor(Aggregate toDo, Actor user) throws DomainEntityRelationshipRuleException
	{
		ensureSatisfiedFor(toDo, user);
	}
}
