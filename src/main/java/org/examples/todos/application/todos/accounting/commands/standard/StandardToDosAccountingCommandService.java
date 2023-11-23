package org.examples.todos.application.todos.accounting.commands.standard;

import java.time.LocalDateTime;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.todos.accounting.ToDoAccessRightsDto;
import org.examples.todos.application.todos.accounting.ToDoDto;
import org.examples.todos.application.todos.accounting.ToDoInfoDto;
import org.examples.todos.application.todos.accounting.commands.ToDoCreatingCommand;
import org.examples.todos.application.todos.accounting.commands.ToDoCreatingCommandResult;
import org.examples.todos.application.todos.accounting.commands.ToDoRemovingCommand;
import org.examples.todos.application.todos.accounting.commands.ToDoRemovingCommandResult;
import org.examples.todos.application.todos.accounting.commands.ToDoUpdatingCommand;
import org.examples.todos.application.todos.accounting.commands.ToDoUpdatingCommandResult;
import org.examples.todos.application.todos.accounting.commands.ToDosAccountingCommandService;
import org.examples.todos.application.users.accounting.UserDto;

public class StandardToDosAccountingCommandService implements ToDosAccountingCommandService {

    @Override
    public ToDoCreatingCommandResult createToDo(ToDoCreatingCommand command) 
        throws NoResourceAccessRightsException {
        
        if (command.getInitiatorId() == 1)
            throw new NoResourceAccessRightsException("initiatorId#1 access denied");

        return new ToDoCreatingCommandResult(
            new ToDoInfoDto(
                new ToDoDto(1, 1, "null", "null", 
                LocalDateTime.now(), null, 
                new UserDto(1, "null", "null", "null"), 
                null, null),
                new ToDoAccessRightsDto(
                    false, false, 
                    false, false) 
                    )
        );
    }

    @Override
    public ToDoUpdatingCommandResult updateToDo(ToDoUpdatingCommand command)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
        
        throw new ResourceNotFoundException("Resource not found for update");
    }

    @Override
    public ToDoRemovingCommandResult removeToDo(ToDoRemovingCommand command) throws NoResourceAccessRightsException {
        
        throw new NoResourceAccessRightsException("Resource access denied");
    }
    
}
