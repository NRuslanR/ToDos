package org.examples.todos.application.users.accounting;

import org.examples.todos.application.roles.accounting.RoleDto;

public record UserInfoDto (
    UserDto user,
    RoleDto role,
    UserCredentials credentials
) {
    
}
