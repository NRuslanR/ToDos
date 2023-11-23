package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.common.entities.rules.access.DomainAggregateRemovingRule;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;

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
