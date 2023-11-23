package org.examples.todos.application.roles.accounting.queries.standard;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.roles.accounting.RoleDto;
import org.examples.todos.application.roles.accounting.queries.AllRolesGettingQuery;
import org.examples.todos.application.roles.accounting.queries.RolesAccountingQueryService;
import org.examples.todos.application.roles.accounting.queries.RoleByIdGettingQuery;

public class StandardRolesAccountingQueryService implements RolesAccountingQueryService {

    @Override
    public Iterable<RoleDto> getAllRoles(AllRolesGettingQuery query) throws NoResourceAccessRightsException {

        throw new UnsupportedOperationException("Unimplemented method 'getAllRoles'");
    }

    @Override
    public RoleDto getRoleById(RoleByIdGettingQuery query)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
    
        throw new UnsupportedOperationException("Unimplemented method 'getRoleById'");
    }
    
}
