package org.examples.todos.domain.common.entities.rules;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityBaseInfo;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateChangingRule;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateRemovingRule;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateViewingRule;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class DomainAggregateWorkingRules<
	Aggregate extends DomainAggregateRoot,
	Actor extends DomainEntity
> 
{
	@Setter(AccessLevel.NONE)
	private final DomainAggregateViewingRule<Aggregate, Actor> viewingRule;
	
	@Setter(AccessLevel.NONE)
	private final DomainAggregateChangingRule<Aggregate, Actor> changingRule;
	
	@Setter(AccessLevel.NONE)
	private final DomainAggregateRemovingRule<Aggregate, Actor> removingRule;
}
