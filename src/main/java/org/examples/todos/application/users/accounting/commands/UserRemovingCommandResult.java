package org.examples.todos.application.users.accounting.commands;

import org.examples.todos.application.users.accounting.UserInfoDto;

public record UserRemovingCommandResult (
    UserInfoDto userInfo
) {

}
