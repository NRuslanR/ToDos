package org.examples.todos.domain.rules.todos.access;

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
