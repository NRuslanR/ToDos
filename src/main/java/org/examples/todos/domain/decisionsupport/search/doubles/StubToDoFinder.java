package org.examples.todos.domain.decisionsupport.search.doubles;

import java.util.ArrayDeque;

import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.BaseToDoFinder;
import org.examples.todos.domain.resources.users.User;

public class StubToDoFinder extends BaseToDoFinder
{
	private ToDoList toDoList;
	
	public StubToDoFinder(ToDoList toDoList)
	{
		this.toDoList = toDoList;
	}
	
	@Override
	public ToDoList doFindAllSubToDosRecursivelyFor(ToDo targetToDo) throws DomainException
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
	public ToDoList doFindUserTodos(User author) throws DomainException 
	{
		return toDoList.getAllUserToDos(author);
	}
	
}
