package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.users.relationships.UserIdentificationRule;

public abstract class ToDoUserAccessRule implements DomainEntityRelationshipRule<ToDo, User> {

	protected final UserIdentificationRule userIdentificationRule;
	
	public ToDoUserAccessRule(UserIdentificationRule userIdentificationRule)
	{
		this.userIdentificationRule = userIdentificationRule;
	}
	
	@Override
	public void ensureSatisfiedFor(ToDo entity, User actor) throws DomainEntityRelationshipRuleException {
		
		ensureUserIsToDoAuthor(actor, entity);
		
	}

	protected void ensureUserIsToDoAuthor(User user, ToDo toDo) {
		
		if (!hasUserPermissionsForToDoAccess(user, toDo))
		{
			throw new ToDoUserAccessRuleException(
				String.format(
					"User \"%s\" can't access rights for To-Do \"%s\" because he isn't same it author",
					user.getFullName(), 
					toDo.getName()
				)
			);
		}
		
	}

	protected boolean hasUserPermissionsForToDoAccess(User user, ToDo toDo) {
		
		return userIdentificationRule.AreUsersSame(user, toDo.getAuthor());
	}

}
