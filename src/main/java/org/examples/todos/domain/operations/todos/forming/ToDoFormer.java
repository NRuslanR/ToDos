package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.common.entities.formers.DomainAggregateRootFormer;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.todos.ToDoWorkingRules;

public interface ToDoFormer extends DomainAggregateRootFormer<ToDo, ToDoInfo, ToDoWorkingRules, User>
{
	default ToDo formToDo(ToDoInfo toDoInfo) throws DomainException
	{
		return formEntity(toDoInfo);
	}
	
	default ToDo formToDo(ToDoInfo toDoInfo, User actor) throws DomainException
	{
		return formAggregateRoot(toDoInfo, actor);
	}
}
