package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateRemovingRule;
import org.examples.todos.domain.resources.users.User;

public interface ToDoUserRemovingRule extends DomainAggregateRemovingRule<ToDo, User> {

	default boolean canToDoRemovedByUser(ToDo toDo, User user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureToDoCanRemovedByUser(ToDo toDo, User user)
	{
		ensureSatisfiedFor(toDo, user);
	}
}
