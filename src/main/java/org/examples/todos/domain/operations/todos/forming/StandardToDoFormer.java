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

public class StandardToDoFormer implements ToDoFormer 
{
	@Override
	public ToDo formToDo(ToDoInfo toDoInfo, User actor) throws DomainException 
	{
		var toDo = formToDo(toDoInfo);
		
		toDo.setActor(actor);
		
		return toDo;
	}

	private ToDoWorkingRules createToDoWorkingRules() 
	{	
		return ToDoWorkingRules.standard();
				
	}

	@Override
	public ToDo formToDo(ToDoInfo toDoInfo) throws DomainException 
	{
		var workingRules = createToDoWorkingRules();
		
		var toDo = new ToDo(toDoInfo);
		
		toDo.setWorkingRules(workingRules);
		
		return toDo;
	}

}
