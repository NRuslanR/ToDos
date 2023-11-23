package org.examples.todos.application.users.accounting.commands.standard;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.users.accounting.commands.UserCreatingCommand;
import org.examples.todos.application.users.accounting.commands.UserCreatingCommandResult;
import org.examples.todos.application.users.accounting.commands.UserRemovingCommand;
import org.examples.todos.application.users.accounting.commands.UserRemovingCommandResult;
import org.examples.todos.application.users.accounting.commands.UserUpdatingCommand;
import org.examples.todos.application.users.accounting.commands.UserUpdatingCommandResult;
import org.examples.todos.application.users.accounting.commands.UsersAccountingCommandService;

public class StandardUsersAccountingCommandService implements UsersAccountingCommandService {

    @Override
    public UserCreatingCommandResult createUser(UserCreatingCommand command) throws NoResourceAccessRightsException {
  
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public UserUpdatingCommandResult updateUser(UserUpdatingCommand command)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
 
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public UserRemovingCommandResult removeUser(UserRemovingCommand command) throws NoResourceAccessRightsException {

        throw new UnsupportedOperationException("Unimplemented method 'removeUser'");
    }
    
}
