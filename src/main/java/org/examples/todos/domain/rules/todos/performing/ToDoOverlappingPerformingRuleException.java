package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoOverlappingPerformingRuleException extends DomainEntityRelationshipRuleException {

	public ToDoOverlappingPerformingRuleException(String message, Throwable t) {
		super(message, t);
	}

	public ToDoOverlappingPerformingRuleException(String message) {
		super(message);
	}

	public ToDoOverlappingPerformingRuleException(Throwable t) {
		super(t);
	}
}
