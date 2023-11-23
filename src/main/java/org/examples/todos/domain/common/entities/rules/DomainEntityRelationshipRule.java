package org.examples.todos.domain.common.entities.rules;

import org.examples.todos.domain.common.entities.DomainEntity;

public interface DomainEntityRelationshipRule<E1 extends DomainEntity, E2 extends DomainEntity>
{
	default boolean isSatisfiedFor(E1 first, E2 second)
	{
		try {
			
			ensureSatisfiedFor(first, second);
			
			return true;
			
		} catch (DomainEntityRelationshipRuleException e) {
			
			return false;
			
		}
	}
	
	void ensureSatisfiedFor(E1 first, E2 second) throws DomainEntityRelationshipRuleException;
}
