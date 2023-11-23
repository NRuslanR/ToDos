package org.examples.todos.domain.todos.rules;

import org.examples.todos.domain.common.entities.rules.DomainAggregateWorkingRules;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateChangingRule;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateRemovingRule;
import org.examples.todos.domain.common.entities.rules.access.DomainAggregateViewingRule;
import org.examples.todos.domain.todos.access.rules.ToDoUserChangingRule;
import org.examples.todos.domain.todos.access.rules.ToDoUserRemovingRule;
import org.examples.todos.domain.todos.access.rules.ToDoUserViewingRule;
import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.todos.performing.rules.ToDoOverlappingPerformingRule;
import org.examples.todos.domain.todos.performing.rules.ToDoUserPerformingRule;
import org.examples.todos.domain.users.actors.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ToDoWorkingRules extends DomainAggregateWorkingRules<ToDo, User> {

	public ToDoWorkingRules(
			ToDoUserViewingRule viewingRule, 
			ToDoUserChangingRule changingRule,
			ToDoUserRemovingRule removingRule,
			ToDoUserPerformingRule performingRule,
			ToDoOverlappingPerformingRule overlappingPerformingRule
	)
	{
		super(viewingRule, changingRule, removingRule);
		
		this.performingRule = performingRule;
		this.overlappingPerformingRule = overlappingPerformingRule;
	}

	public ToDoUserViewingRule getViewingRule()
	{
		return (ToDoUserViewingRule) super.getViewingRule();
	}
	
	public ToDoUserChangingRule getChangingRule()
	{
		return (ToDoUserChangingRule) super.getChangingRule();
	}
	
	public ToDoUserRemovingRule getRemovingRule()
	{
		return (ToDoUserRemovingRule) super.getRemovingRule();
	}
	
	private final ToDoUserPerformingRule performingRule;
	private final ToDoOverlappingPerformingRule overlappingPerformingRule;
}
