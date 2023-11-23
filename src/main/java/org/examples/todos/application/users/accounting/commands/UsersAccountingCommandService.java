package org.examples.todos.application.users.accounting.commands;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;

public interface UsersAccountingCommandService {
    
    UserCreatingCommandResult createUser(UserCreatingCommand command)
        throws NoResourceAccessRightsException;

    UserUpdatingCommandResult updateUser(UserUpdatingCommand command)
        throws ResourceNotFoundException, NoResourceAccessRightsException;

    UserRemovingCommandResult removeUser(UserRemovingCommand command)
        throws NoResourceAccessRightsException;    
}
