package org.examples.todos.domain.operations.todos.forming;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;

public interface ToDoFormer {

	ToDo formToDo(ToDoInfo toDoInfo, User author) throws DomainException;
}
