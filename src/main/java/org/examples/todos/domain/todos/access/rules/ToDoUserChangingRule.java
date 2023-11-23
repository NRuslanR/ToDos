package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.common.entities.rules.access.DomainAggregateChangingRule;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;

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
