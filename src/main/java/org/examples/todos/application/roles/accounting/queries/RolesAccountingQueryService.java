package org.examples.todos.application.roles.accounting.queries;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.roles.accounting.RoleDto;

public interface RolesAccountingQueryService {
    
    Iterable<RoleDto> getAllRoles(AllRolesGettingQuery query)
        throws NoResourceAccessRightsException;

    RoleDto getRoleById(RoleByIdGettingQuery query)
        throws ResourceNotFoundException, NoResourceAccessRightsException;
}
