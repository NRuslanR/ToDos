package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.users.relationships.UserIdentificationRule;

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
	
	@Override
	protected boolean hasUserPermissionsForToDoAccess(User user, ToDo toDo) {
		
		return 
				super.hasUserPermissionsForToDoAccess(user, toDo) ||
				user.canEditForeignToDos();
	}
}
