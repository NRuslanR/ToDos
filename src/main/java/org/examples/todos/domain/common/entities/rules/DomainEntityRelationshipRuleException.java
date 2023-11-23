package org.examples.todos.domain.common.entities.rules;

import org.examples.todos.domain.common.errors.DomainException;

public class DomainEntityRelationshipRuleException extends DomainException {

	private static final long serialVersionUID = 1L;

	public DomainEntityRelationshipRuleException(String message, Throwable t) {
		super(message, t);
	}

	public DomainEntityRelationshipRuleException(String message) {
		super(message);
	}

	public DomainEntityRelationshipRuleException(Throwable t) {
		super(t);
	}
}
