package org.examples.todos.domain.operations.todos.performing;

import java.util.ArrayDeque;

import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;

public class StandardToDoOverlappingPerformingService implements ToDoOverlappingPerformingService 
{
	private final ToDoFinder toDoFinder;
	
	public StandardToDoOverlappingPerformingService(ToDoFinder toDoFinder)
	{
		this.toDoFinder = toDoFinder;
	}
	
	@Override
	public ToDoList performToDo(ToDo toDo) throws DomainException 
	{	
		toDo.perform();
		
		var performedToDoList = performAllSubToDosRecursivelyFor(toDo);	
		
		performedToDoList.add(toDo);
		
		return performedToDoList;
	}

	private ToDoList performAllSubToDosRecursivelyFor(ToDo toDo) 
	{
		var subToDoList = findAllSubToDosRecursivelyFor(toDo);
		
		var performedToDos = new ArrayDeque<ToDo>();
		
		performedToDos.add(toDo);

		while (!performedToDos.isEmpty())
		{
			var overlappingToDo = performedToDos.remove();
			
			var toDosForOverlapping = subToDoList.getAllSubToDosFor(overlappingToDo);
			
			performToDosByOverlapping(toDosForOverlapping, overlappingToDo);
			
			performedToDos.addAll(IterableUtils.toList(toDosForOverlapping));
		}
		
		return subToDoList;
	}

	private void performToDosByOverlapping(ToDoList toDosForOverlapping, ToDo overlappingToDo) {
		
		toDosForOverlapping.forEach(toDo -> {
			
			if (!toDo.isPerformed())
				toDo.performByOverlapping(overlappingToDo);
			
		});
	}

	private ToDoList findAllSubToDosRecursivelyFor(ToDo targetToDo) 
	{
		return toDoFinder.findAllSubToDosRecursivelyFor(targetToDo);
	}

}
