package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateViewingRule;
import org.examples.todos.domain.resources.users.User;

public interface ToDoUserViewingRule extends DomainAggregateViewingRule<ToDo, User> {
	
	default boolean canToDoViewedByUser(ToDo toDo, User user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureToDoCanViewedByUser(ToDo toDo, User user)
	{
		ensureSatisfiedFor(toDo, user);
	}
}
