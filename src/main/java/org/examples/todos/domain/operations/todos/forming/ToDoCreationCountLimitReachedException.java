package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;

public class ToDoCreationCountLimitReachedException extends DomainEntityRelationshipRuleException
{

	protected ToDoCreationCountLimitReachedException(String message, Throwable t) 
	{
		super(message, t);
	}

	protected ToDoCreationCountLimitReachedException(String message) 
	{
		super(message);
	}

	protected ToDoCreationCountLimitReachedException(Throwable t) 
	{
		super(t);
	}

}
