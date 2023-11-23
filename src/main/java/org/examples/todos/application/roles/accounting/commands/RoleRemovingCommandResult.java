package org.examples.todos.application.roles.accounting.commands;

import org.examples.todos.application.roles.accounting.RoleDto;

public record RoleRemovingCommandResult (
    RoleDto role
) {

}
