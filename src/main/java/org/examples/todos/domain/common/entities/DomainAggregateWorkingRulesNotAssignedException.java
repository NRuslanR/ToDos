package org.examples.todos.domain.common.entities;

import org.examples.todos.domain.common.errors.DomainException;

public class DomainAggregateWorkingRulesNotAssignedException extends DomainException 
{

	protected DomainAggregateWorkingRulesNotAssignedException(String message, Throwable t) {
		super(message, t);
	}

	protected DomainAggregateWorkingRulesNotAssignedException(String message) {
		super(message);
	}

	protected DomainAggregateWorkingRulesNotAssignedException(Throwable t) {
		super(t);
	}

}
