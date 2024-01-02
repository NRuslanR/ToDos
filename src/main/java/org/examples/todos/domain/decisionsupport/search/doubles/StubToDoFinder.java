package org.examples.todos.domain.decisionsupport.search.doubles;

import java.util.ArrayDeque;

import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;
import org.examples.todos.domain.resources.users.User;

public class StubToDoFinder implements ToDoFinder
{
	private ToDoList toDoList;
	
	public StubToDoFinder(ToDoList toDoList)
	{
		this.toDoList = toDoList;
	}
	
	@Override
	public ToDoList findAllSubToDosRecursivelyFor(ToDo targetToDo) throws NullPointerException 
	{
		var foundToDoList = new ToDoList();
		
		var toDoQueue = new ArrayDeque<ToDo>();
		
		toDoQueue.push(targetToDo);
		
		while (!toDoQueue.isEmpty())
		{
			var toDo = toDoQueue.remove();
			
			var subToDoList = toDoList.getAllSubToDosFor(toDo);
			
			var subToDos = IterableUtils.toList(subToDoList);
			
			foundToDoList.addAll(subToDos);
			
			toDoQueue.addAll(subToDos);
		}
		
		return foundToDoList;
	}

	@Override
	public ToDoList findUserTodos(User author) throws NullPointerException {

		return toDoList.getAllUserToDos(author);
	}
	
}
