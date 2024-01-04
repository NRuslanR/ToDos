package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.users.relationships.UserIdentificationRule;

public class StandardToDoUserPerformingRule implements ToDoUserPerformingRule {

	private final UserIdentificationRule userIdentificationRule;
	
	public StandardToDoUserPerformingRule(UserIdentificationRule userIdentificationRule)
	{
		this.userIdentificationRule = userIdentificationRule;
	}
	
	@Override
	public void ensureSatisfiedFor(ToDo toDo, User user) throws DomainEntityRelationshipRuleException {
		
		ensureUserIsToDoAuthor(user, toDo);
		ensureToDoIsNotPeformed(toDo, user);
		
	}

	private void ensureUserIsToDoAuthor(User user, ToDo toDo) {
		
		if (!userIdentificationRule.AreUsersSame(user, toDo.getAuthor()))
		{
			throw new ToDoActorHasNotPerformingRightsException(
				"User \"" + user.getFullName() + 
				"\" isn't To-Do \"" + toDo.getName() + "\"'s author \"" + 
				toDo.getAuthor().getFullName() + "\""
			);
		}
	}

	private void ensureToDoIsNotPeformed(ToDo toDo, User user) {
		
		if (toDo.isPerformed())
		{
			throw new ToDoIsAlreadyPerformedException(
				"To-Do \"" + toDo.getName() + "\" is already performed"
			);
		}
	}
}
