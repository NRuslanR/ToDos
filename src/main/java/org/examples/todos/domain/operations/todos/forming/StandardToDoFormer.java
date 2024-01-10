package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.common.entities.formers.BaseDomainAggregateRootFormer;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.todos.ToDoWorkingRules;

public class StandardToDoFormer 
	extends BaseDomainAggregateRootFormer<ToDo, ToDoInfo, ToDoWorkingRules, User> 
	implements ToDoFormer
{

	@Override
	public ToDo formEntity(ToDoInfo info) throws DomainException 
	{
		return new ToDo(info);
	}

	@Override
	protected ToDoWorkingRules getWorkingRules() 
	{
		return ToDoWorkingRules.standard();
	}

}
