package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoIsAlreadyPerformedException extends DomainEntityRelationshipRuleException 
{

	protected ToDoIsAlreadyPerformedException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoIsAlreadyPerformedException(String message) {
		super(message);
	}

	protected ToDoIsAlreadyPerformedException(Throwable t) {
		super(t);
	}

}
