package org.examples.todos.web.api.usecases.todos.accounting.controllers;

import org.examples.todos.application.todos.accounting.ToDoDto;
import org.examples.todos.application.todos.accounting.ToDoInfoDto;
import org.examples.todos.application.todos.accounting.commands.ToDoCreatingCommand;
import org.examples.todos.application.todos.accounting.commands.ToDoRemovingCommand;
import org.examples.todos.application.todos.accounting.commands.ToDoUpdatingCommand;
import org.examples.todos.application.todos.accounting.commands.ToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.queries.AllTodosGettingQuery;
import org.examples.todos.application.todos.accounting.queries.NewToDoInstanceGettingQuery;
import org.examples.todos.application.todos.accounting.queries.ToDosAccountingQueryService;
import org.examples.todos.application.todos.accounting.queries.TodoByIdGettingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// think about client id passing
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/accounting/todos", produces = "application/json")
public class ToDosAccountingApiController {
    
	private ToDosAccountingQueryService toDosAccountingQueryService;
	private ToDosAccountingCommandService toDosAccountingCommandService;

	@Autowired
	public ToDosAccountingApiController(
		ToDosAccountingQueryService toDosAccountingQueryService,	
		ToDosAccountingCommandService toDosAccountingCommandService)
	{
		this.toDosAccountingQueryService = toDosAccountingQueryService;
		this.toDosAccountingCommandService = toDosAccountingCommandService;
	}

    @RequestMapping(method = RequestMethod.GET, path = "/")
	@ResponseStatus(code = HttpStatus.OK)
    public Iterable<ToDoInfoDto> getAllTodos() throws Exception
    {     
		long requesterId = 0;

		return toDosAccountingQueryService.getAllTodos(new AllTodosGettingQuery(requesterId));
    }

	@RequestMapping(method = RequestMethod.GET, path = "/{todoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ToDoInfoDto getTodoById(
		@PathVariable(name = "todoId") long todoId
	) throws Exception
	{
		long requesterId = 0;

		return toDosAccountingQueryService.getTodoById(new TodoByIdGettingQuery(todoId, requesterId));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/blank")
	@ResponseStatus(code = HttpStatus.OK)
	public ToDoInfoDto getBlankToDo(@RequestHeader(name = "clientId") long clientId) throws Exception
	{
		return toDosAccountingQueryService.getNewToDoInstance(
			new NewToDoInstanceGettingQuery(clientId)
		);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ToDoInfoDto createToDo(
		@RequestBody ToDoCreatingCommand toDoCreatingCommand
	) throws Exception
	{
		var result = toDosAccountingCommandService.createToDo(toDoCreatingCommand);

		return result.toDoInfo();
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{toDoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ToDoInfoDto updateToDo(
		@PathVariable(name = "toDoId") long toDoId, 
		@RequestBody ToDoUpdatingCommand toDoUpdatingCommand
	) throws Exception
	{
		var result = toDosAccountingCommandService.updateToDo(toDoUpdatingCommand);

		return result.toDoInfo();
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{toDoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ToDoDto removeToDo(
		@PathVariable(name = "toDoId") long toDoId
	) throws Exception
	{
		var result = toDosAccountingCommandService.removeToDo(ToDoRemovingCommand.of(toDoId));
		
		return result.toDo();
	}
}
