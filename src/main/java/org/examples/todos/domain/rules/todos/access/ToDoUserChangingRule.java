package org.examples.todos.domain.rules.todos.access;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoNoteList;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateChangingRule;
import org.examples.todos.domain.resources.users.User;

public interface ToDoUserChangingRule extends DomainAggregateChangingRule<ToDo, User> {

	default boolean canToDoChangedByUser(ToDo toDo, User user)
	{
		return isSatisfiedFor(toDo, user);
	}
	
	default void ensureToDoCanBeChangedByUser(ToDo toDo, User user) throws DomainEntityRelationshipRuleException
	{
		ensureSatisfiedFor(toDo, user);
	}
	
	default boolean canUserAssignNewToDoNote(User user, ToDo toDo) 
	{
		try 
		{
			ensureUserCanAssignNewToDoNote(user, toDo);
			
			return true;
		} 
		
		catch (DomainEntityRelationshipRuleException exception) {
			
			return false;
		}
	}
	
	void ensureUserCanAssignNewToDoNote(User user, ToDo toDo) throws DomainEntityRelationshipRuleException;
	void ensureToDoNoteListValid(ToDoNoteList toDoNoteList, User user) throws DomainEntityRelationshipRuleException;
}
