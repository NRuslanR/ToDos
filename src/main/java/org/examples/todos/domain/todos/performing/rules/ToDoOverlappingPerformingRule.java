package org.examples.todos.domain.todos.performing.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;

public interface ToDoOverlappingPerformingRule extends DomainEntityRelationshipRule<ToDo, ToDo> {

	
	default boolean canToDoPerformingOverlappedByOther(ToDo targetToDo, ToDo overlappingToDo)
	{
		return isSatisfiedFor(targetToDo, overlappingToDo);
	}
	
	default void ensureToDoPerformingCanBeOverlappedByOther(
		ToDo targetToDo, ToDo overlappingToDo, User initiator
		
	) throws ToDoOverlappingPerformingRuleException
	{
		ensureSatisfiedFor(targetToDo, overlappingToDo);
	}
}
