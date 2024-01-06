package org.examples.todos.domain.rules.todos;

import org.examples.todos.domain.rules.todos.access.StandardToDoUserChangingRule;
import org.examples.todos.domain.rules.todos.access.StandardToDoUserRemovingRule;
import org.examples.todos.domain.rules.todos.access.StandardToDoUserViewingRule;
import org.examples.todos.domain.rules.todos.performing.StandardToDoOverlappingPerformingRule;
import org.examples.todos.domain.rules.todos.performing.StandardToDoUserPerformingRule;
import org.examples.todos.domain.rules.users.relationships.StandardUserIdentificationRule;
import org.examples.todos.domain.rules.users.relationships.UserIdentificationRule;

public class StandardToDoWorkingRules extends ToDoWorkingRules
{
	public StandardToDoWorkingRules() 
	{
		this(new StandardUserIdentificationRule());
	}
	
	private StandardToDoWorkingRules(UserIdentificationRule userIdentificationRule)
	{
		super(
			new StandardToDoUserViewingRule(userIdentificationRule), 
			new StandardToDoUserChangingRule(userIdentificationRule), 
			new StandardToDoUserRemovingRule(userIdentificationRule), 
			new StandardToDoUserPerformingRule(userIdentificationRule), 
			new StandardToDoOverlappingPerformingRule()
		);
	}

}
