package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;
import org.examples.todos.domain.users.relationships.rules.UserIdentificationRule;

public class StandardToDoUserChangingRule extends ToDoUserAccessRule implements ToDoUserChangingRule {

	public StandardToDoUserChangingRule(UserIdentificationRule userIdentificationRule) {
		super(userIdentificationRule);
	}

	@Override
	public void ensureSatisfiedFor(ToDo entity, User actor) throws DomainEntityRelationshipRuleException {
	
		super.ensureSatisfiedFor(entity, actor);
		
		ensureToDoIsNotPerformed(entity, actor);
	}

	private void ensureToDoIsNotPerformed(ToDo toDo, User user) {
		
		if (toDo.isPerformed())
		{
			throw new DomainEntityRelationshipRuleException(
				"To-Do \"" + toDo.getName() + "\" is already performed"
			);
		}
	}

}
