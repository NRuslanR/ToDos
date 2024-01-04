package org.examples.todos.domain.actors;

import org.examples.todos.domain.common.errors.DomainException;

public class ToDoParentItSelfException extends DomainException
{

	protected ToDoParentItSelfException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoParentItSelfException(String message) {
		super(message);
	}

	protected ToDoParentItSelfException(Throwable t) {
		super(t);
	}

}
