package org.examples.todos.application.todos.accounting.queries.standard;

import java.util.Arrays;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.todos.accounting.ToDoAccessRightsDto;
import org.examples.todos.application.todos.accounting.ToDoDto;
import org.examples.todos.application.todos.accounting.ToDoInfoDto;
import org.examples.todos.application.todos.accounting.ToDosAccountingAccessRightsDto;
import org.examples.todos.application.todos.accounting.queries.AllTodosGettingQuery;
import org.examples.todos.application.todos.accounting.queries.NewToDoInstanceGettingQuery;
import org.examples.todos.application.todos.accounting.queries.ToDosAccountingAccessRightsGettingQuery;
import org.examples.todos.application.todos.accounting.queries.ToDosAccountingQueryService;
import org.examples.todos.application.todos.accounting.queries.TodoByIdGettingQuery;

public class StandardToDosAccountingQueryService implements ToDosAccountingQueryService {

    @Override
    public ToDosAccountingAccessRightsDto getToDosAccountingAccessRights(
            ToDosAccountingAccessRightsGettingQuery query) {
        
        return new ToDosAccountingAccessRightsDto(false, false);
    }

    @Override
    public ToDoInfoDto getNewToDoInstance(NewToDoInstanceGettingQuery query) throws NoResourceAccessRightsException {
        
        return new ToDoInfoDto(
            new ToDoDto(0, 0, "null", "null", 
            null, null, null, null, null),
            new ToDoAccessRightsDto(false, false, false, false)
        );
    }

    @Override
    public Iterable<ToDoInfoDto> getAllTodos(AllTodosGettingQuery query) {
        
        return Arrays.asList();
    }

    @Override
    public ToDoInfoDto getTodoById(TodoByIdGettingQuery query)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
        
        if (query.getToDoId() == 1)
            throw new ResourceNotFoundException("todo#1 is not found");

        if (query.getToDoId() == 2)
            throw new NoResourceAccessRightsException("todo#2 access is denied");
            
        return getNewToDoInstance(
            new NewToDoInstanceGettingQuery(query.getRequesterId())
        );
    }
    
}
