package org.examples.todos.domain.actors;

import org.examples.todos.domain.common.errors.DomainException;

public class ToDoNameIncorrectException extends DomainException
{

	protected ToDoNameIncorrectException(String message, Throwable t) {
		super(message, t);
	}

	protected ToDoNameIncorrectException(String message) {
		super(message);
	}

	protected ToDoNameIncorrectException(Throwable t) {
		super(t);
	}

}
