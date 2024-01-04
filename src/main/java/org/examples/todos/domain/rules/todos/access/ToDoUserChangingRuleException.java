package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoUserChangingRuleException extends ToDoUserAccessRuleException
{
	protected ToDoUserChangingRuleException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoUserChangingRuleException(String message) {
		super(message);
	}

	protected ToDoUserChangingRuleException(Throwable t) {
		super(t);
	}
}
