package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.users.relationships.UserIdentificationRule;

public class StandardToDoUserViewingRule extends ToDoUserAccessRule implements ToDoUserViewingRule {

	public StandardToDoUserViewingRule(UserIdentificationRule userIdentificationRule) {
		super(userIdentificationRule);
	}

	@Override
	protected boolean hasUserPermissionsForToDoAccess(User user, ToDo toDo) {
		
		return 
				super.hasUserPermissionsForToDoAccess(user, toDo) ||
				user.canEditForeignToDos() ||
				user.canRemoveForeignToDos();
	}
	
	

}
