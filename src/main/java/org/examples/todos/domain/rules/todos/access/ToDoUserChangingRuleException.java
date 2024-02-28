package org.examples.todos.domain.rules.todos.access;

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
