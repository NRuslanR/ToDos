package org.examples.todos.application.todos.accounting.queries;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.todos.accounting.ToDoInfoDto;
import org.examples.todos.application.todos.accounting.ToDosAccountingAccessRightsDto;

public interface ToDosAccountingQueryService {
	
	ToDosAccountingAccessRightsDto getToDosAccountingAccessRights(
		ToDosAccountingAccessRightsGettingQuery query
	);

	ToDoInfoDto getNewToDoInstance(
		NewToDoInstanceGettingQuery query
	) throws NoResourceAccessRightsException;

	Iterable<ToDoInfoDto> getAllTodos(AllTodosGettingQuery query) 
		throws NoResourceAccessRightsException;

	ToDoInfoDto getTodoById(
		TodoByIdGettingQuery query
	) throws ResourceNotFoundException, NoResourceAccessRightsException;
}
