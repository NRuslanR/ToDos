package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class OverlappingToDoIsNotParentException extends ToDoOverlappingPerformingRuleException
{

	protected OverlappingToDoIsNotParentException(String message, Throwable t) {
		super(message, t);
	}

	protected OverlappingToDoIsNotParentException(String message) {
		super(message);
	}

	protected OverlappingToDoIsNotParentException(Throwable t) {
		super(t);
	}

}
