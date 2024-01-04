package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class OverlappingToDoIsNotPerformedException extends ToDoOverlappingPerformingRuleException 
{

	protected OverlappingToDoIsNotPerformedException(String message, Throwable t) {
		super(message, t);
	}

	protected OverlappingToDoIsNotPerformedException(String message) {
		super(message);
	}

	protected OverlappingToDoIsNotPerformedException(Throwable t) {
		super(t);
	}

}
