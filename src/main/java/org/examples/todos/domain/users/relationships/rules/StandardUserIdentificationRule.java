package org.examples.todos.domain.users.relationships.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.users.actors.User;

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
