package org.examples.todos.application.roles.accounting.commands.standard;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.roles.accounting.commands.RoleCreatingCommand;
import org.examples.todos.application.roles.accounting.commands.RoleCreatingCommandResult;
import org.examples.todos.application.roles.accounting.commands.RoleRemovingCommand;
import org.examples.todos.application.roles.accounting.commands.RoleRemovingCommandResult;
import org.examples.todos.application.roles.accounting.commands.RoleUpdatingCommand;
import org.examples.todos.application.roles.accounting.commands.RoleUpdatingCommandResult;
import org.examples.todos.application.roles.accounting.commands.RolesAccountingCommandService;

public class StandardRolesAccountingCommandService implements RolesAccountingCommandService {

    @Override
    public RoleCreatingCommandResult createRole(RoleCreatingCommand command) throws NoResourceAccessRightsException {

        throw new UnsupportedOperationException("Unimplemented method 'createRole'");
    }

    @Override
    public RoleUpdatingCommandResult updateRole(RoleUpdatingCommand command)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
      
        throw new UnsupportedOperationException("Unimplemented method 'updateRole'");
    }

    @Override
    public RoleRemovingCommandResult removeRole(RoleRemovingCommand command) throws NoResourceAccessRightsException {

        throw new UnsupportedOperationException("Unimplemented method 'removeRole'");
    }
    
}
