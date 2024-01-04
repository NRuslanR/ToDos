package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.resources.users.User;

public interface ToDoUserPerformingRule extends DomainEntityRelationshipRule<ToDo, User> {
	
	default boolean canToDoPerformedByUser(ToDo toDo, User user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureToDoCanPerformedByUser(ToDo toDo, User user) throws DomainEntityRelationshipRuleException
	{
		ensureSatisfiedFor(toDo, user);
	}
}
