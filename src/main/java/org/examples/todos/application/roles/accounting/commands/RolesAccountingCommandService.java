package org.examples.todos.application.roles.accounting.commands;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;

public interface RolesAccountingCommandService {
    
    RoleCreatingCommandResult createRole(RoleCreatingCommand command)
        throws NoResourceAccessRightsException;

    RoleUpdatingCommandResult updateRole(RoleUpdatingCommand command)
        throws ResourceNotFoundException, NoResourceAccessRightsException;

    RoleRemovingCommandResult removeRole(RoleRemovingCommand command)
        throws NoResourceAccessRightsException;

}
