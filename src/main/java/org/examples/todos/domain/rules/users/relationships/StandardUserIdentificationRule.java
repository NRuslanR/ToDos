package org.examples.todos.domain.rules.users.relationships;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.resources.users.User;

public class StandardUserIdentificationRule implements UserIdentificationRule {

	@Override
	public void ensureSatisfiedFor(User first, User second) throws DomainEntityRelationshipRuleException {
		
		if (!first.equals(second))
		{
			throw new DomainEntityRelationshipRuleException(
				"User \"" + first.getFullName() + "\" isn't same user \"" + second.getFullName() + "\""
			);
		}
		
	}

}
