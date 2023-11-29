package org.examples.todos.domain.rules.users.relationships;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.resources.users.User;

public interface UserIdentificationRule extends DomainEntityRelationshipRule<User, User> {

	default boolean AreUsersSame(User first, User second)
	{
		return isSatisfiedFor(first, second);
	}
	
	default void ensureUsersAreSame(User first, User second) throws DomainEntityRelationshipRuleException
	{
		ensureSatisfiedFor(first, second);
	}
}
