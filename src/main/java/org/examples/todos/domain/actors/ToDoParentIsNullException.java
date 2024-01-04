package org.examples.todos.domain.actors;

import org.examples.todos.domain.common.errors.DomainException;

public class ToDoParentIsNullException extends DomainException 
{

	protected ToDoParentIsNullException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoParentIsNullException(String message) {
		super(message);
	}

	protected ToDoParentIsNullException(Throwable t) {
		super(t);
	}

}
