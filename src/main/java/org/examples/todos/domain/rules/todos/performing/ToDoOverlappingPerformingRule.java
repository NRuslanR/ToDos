package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.resources.users.User;

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
