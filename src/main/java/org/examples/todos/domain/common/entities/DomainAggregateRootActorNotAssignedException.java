package org.examples.todos.domain.common.entities;

import org.examples.todos.domain.common.errors.DomainException;

public class DomainAggregateRootActorNotAssignedException extends DomainException 
{

	protected DomainAggregateRootActorNotAssignedException(String message, Throwable t) {
		super(message, t);
	}

	protected DomainAggregateRootActorNotAssignedException(String message) {
		super(message);
	}

	protected DomainAggregateRootActorNotAssignedException(Throwable t) {
		super(t);
	}

}
