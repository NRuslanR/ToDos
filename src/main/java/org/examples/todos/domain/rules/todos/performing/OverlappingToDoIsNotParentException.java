package org.examples.todos.domain.rules.todos.performing;

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
