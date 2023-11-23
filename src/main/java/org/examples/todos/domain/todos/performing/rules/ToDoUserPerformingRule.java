package org.examples.todos.domain.todos.performing.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;

public interface ToDoUserPerformingRule extends DomainEntityRelationshipRule<ToDo, User> {
	
	default boolean canToDoPerformedByUser(ToDo toDo, User user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureToDoCanPerformedByUser(ToDo toDo, User user)
	{
		ensureSatisfiedFor(toDo, user);
	}
}
