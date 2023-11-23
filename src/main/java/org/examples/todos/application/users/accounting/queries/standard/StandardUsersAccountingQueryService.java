package org.examples.todos.application.users.accounting.queries.standard;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.users.accounting.UserDto;
import org.examples.todos.application.users.accounting.UserInfoDto;
import org.examples.todos.application.users.accounting.queries.AllUsersGettingQuery;
import org.examples.todos.application.users.accounting.queries.UserByIdGettingQuery;
import org.examples.todos.application.users.accounting.queries.UsersAccountingQueryService;

public class StandardUsersAccountingQueryService implements UsersAccountingQueryService {

    @Override
    public Iterable<UserInfoDto> getAllUserFullInfos(AllUsersGettingQuery query)
            throws NoResourceAccessRightsException {
      
        throw new UnsupportedOperationException("Unimplemented method 'getAllUserFullInfos'");
    }

    @Override
    public UserInfoDto getUserFullInfoById(UserByIdGettingQuery query)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
     
        throw new UnsupportedOperationException("Unimplemented method 'getUserFullInfoById'");
    }

    @Override
    public UserDto getUserById(UserByIdGettingQuery query)
            throws ResourceNotFoundException, NoResourceAccessRightsException {
    
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }
    
}
