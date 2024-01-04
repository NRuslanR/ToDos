package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoUserAccessRuleException extends DomainEntityRelationshipRuleException
{

	protected ToDoUserAccessRuleException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoUserAccessRuleException(String message) {
		super(message);
	}

	protected ToDoUserAccessRuleException(Throwable t) {
		super(t);
	}

}
