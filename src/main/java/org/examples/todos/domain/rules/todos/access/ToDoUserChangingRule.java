package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateChangingRule;
import org.examples.todos.domain.resources.users.User;

public interface ToDoUserChangingRule extends DomainAggregateChangingRule<ToDo, User> {

	default boolean canToDoChangedByUser(ToDo toDo, User user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureToDoCanBeChangedByUser(ToDo toDo, User user)
	{
		ensureSatisfiedFor(toDo, user);
	}
}
