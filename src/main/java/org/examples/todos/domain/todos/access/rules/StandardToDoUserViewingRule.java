package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.users.relationships.rules.UserIdentificationRule;

public class StandardToDoUserViewingRule extends ToDoUserAccessRule implements ToDoUserViewingRule {

	public StandardToDoUserViewingRule(UserIdentificationRule userIdentificationRule) {
		super(userIdentificationRule);
	}

}
