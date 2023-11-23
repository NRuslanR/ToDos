package org.examples.todos.domain.todos.performing.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.common.errors.DomainException;

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
