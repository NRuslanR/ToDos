package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.common.entities.rules.access.DomainAggregateViewingRule;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;

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
