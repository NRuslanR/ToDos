package org.examples.todos.application.users.accounting.queries;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.application.users.accounting.UserDto;
import org.examples.todos.application.users.accounting.UserInfoDto;

public interface UsersAccountingQueryService {
    
    Iterable<UserInfoDto> getAllUserFullInfos(AllUsersGettingQuery query) 
        throws NoResourceAccessRightsException;

    UserInfoDto getUserFullInfoById(UserByIdGettingQuery query) 
        throws ResourceNotFoundException, NoResourceAccessRightsException;

    UserDto getUserById(UserByIdGettingQuery query)
        throws ResourceNotFoundException, NoResourceAccessRightsException;
}
