package org.examples.todos.domain.decisionsupport.search;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.resources.users.User;

public interface ToDoFinder 
{
	ToDoList findAllSubToDosRecursivelyFor(ToDo targetToDo) throws NullPointerException;
	ToDoList findUserTodos(User author) throws NullPointerException;
}
