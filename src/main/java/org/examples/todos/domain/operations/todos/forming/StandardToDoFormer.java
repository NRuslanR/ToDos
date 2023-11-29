package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.todos.ToDoWorkingRules;
import org.examples.todos.domain.rules.todos.access.StandardToDoUserChangingRule;
import org.examples.todos.domain.rules.todos.access.StandardToDoUserRemovingRule;
import org.examples.todos.domain.rules.todos.access.StandardToDoUserViewingRule;
import org.examples.todos.domain.rules.todos.performing.StandardToDoOverlappingPerformingRule;
import org.examples.todos.domain.rules.todos.performing.StandardToDoUserPerformingRule;
import org.examples.todos.domain.rules.users.relationships.StandardUserIdentificationRule;

public class StandardToDoFormer implements ToDoFormer {

	@Override
	public ToDo formToDo(ToDoInfo toDoInfo, User author) throws DomainException {
		
		var workingRules = createToDoWorkingRules();
		
		return new ToDo(toDoInfo, workingRules, author);
	}

	private ToDoWorkingRules createToDoWorkingRules() {
		
		var userIdentificationRule = new StandardUserIdentificationRule();
		
		return new ToDoWorkingRules(
			new StandardToDoUserViewingRule(userIdentificationRule),
			new StandardToDoUserChangingRule(userIdentificationRule),
			new StandardToDoUserRemovingRule(userIdentificationRule),
			new StandardToDoUserPerformingRule(userIdentificationRule),
			new StandardToDoOverlappingPerformingRule()
		);
				
	}

}
