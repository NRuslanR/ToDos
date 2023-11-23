package org.examples.todos.domain.todos.access.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRule;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.users.actors.User;
import org.examples.todos.domain.users.relationships.rules.UserIdentificationRule;

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
		
		if (!userIdentificationRule.AreUsersSame(user, toDo.getAuthor()))
		{
			throw new DomainEntityRelationshipRuleException(
				String.format(
					"User \"%s\" can't access rights for To-Do \"%s\" because he isn't same it author",
					user.getFullName(), 
					toDo.getName()
				)
			);
		}
		
	}

}
