package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StandardToDoParentAssigningService implements ToDoParentAssigningService 
{
	private final ToDoFinder toDoFinder;
	
	@Override
	public void assignToDoParent(ToDo targetToDo, ToDo parentToDo) throws DomainException {
		
		var toDoList = toDoFinder.findAllSubToDosRecursivelyFor(targetToDo);
		
		if (toDoList.contains(parentToDo))
		{
			throw new DomainException(
				"Invalid parent-child relationship because " +
				"To-Do \"" + parentToDo.getName() + "\" " +
				"is child of To-Do\"" + targetToDo.getName() + "\""
			);
		}
		
		targetToDo.setParentToDoId(parentToDo.getId());
	}
}
