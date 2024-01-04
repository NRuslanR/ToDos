package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoNoteCreationCountLimitReachedException extends ToDoUserChangingRuleException
{

	protected ToDoNoteCreationCountLimitReachedException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoNoteCreationCountLimitReachedException(String message) {
		super(message);
	}

	protected ToDoNoteCreationCountLimitReachedException(Throwable t) {
		super(t);
	}
	
}
