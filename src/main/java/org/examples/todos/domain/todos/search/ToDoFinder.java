package org.examples.todos.domain.todos.search;

import org.examples.todos.domain.todos.actors.ToDo;
import org.examples.todos.domain.todos.actors.ToDoList;

public interface ToDoFinder 
{
	ToDoList findAllSubToDosRecursivelyFor(ToDo targetToDo) throws NullPointerException;
}
