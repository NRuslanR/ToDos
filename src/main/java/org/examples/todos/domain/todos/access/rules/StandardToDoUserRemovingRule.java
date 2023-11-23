package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.users.relationships.rules.UserIdentificationRule;

public class StandardToDoUserRemovingRule extends ToDoUserAccessRule implements ToDoUserRemovingRule {

	public StandardToDoUserRemovingRule(UserIdentificationRule userIdentificationRule) {
		super(userIdentificationRule);
	}

}
