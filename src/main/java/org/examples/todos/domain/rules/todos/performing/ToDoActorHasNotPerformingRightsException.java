package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoActorHasNotPerformingRightsException extends DomainEntityRelationshipRuleException
{

	protected ToDoActorHasNotPerformingRightsException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoActorHasNotPerformingRightsException(String message) {
		super(message);
	}

	protected ToDoActorHasNotPerformingRightsException(Throwable t) {
		super(t);
	}

}
