package org.examples.todos.web.api.usecases.users.accounting.controllers;

import org.examples.todos.application.users.accounting.UserInfoDto;
import org.examples.todos.application.users.accounting.commands.UserCreatingCommand;
import org.examples.todos.application.users.accounting.commands.UserRemovingCommand;
import org.examples.todos.application.users.accounting.commands.UserUpdatingCommand;
import org.examples.todos.application.users.accounting.commands.UsersAccountingCommandService;
import org.examples.todos.application.users.accounting.queries.AllUsersGettingQuery;
import org.examples.todos.application.users.accounting.queries.UserByIdGettingQuery;
import org.examples.todos.application.users.accounting.queries.UsersAccountingQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/system/accounting/users")
@RequiredArgsConstructor
public class UsersAccountingPrivilegedApiController {

    private final UsersAccountingQueryService usersAccountingQueryService;
    private final UsersAccountingCommandService usersAccountingCommandService;

    @GetMapping(path = "/")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<UserInfoDto> getAllUserFullInfos() throws Exception
    {
        long requesterId = 0;

        return usersAccountingQueryService.getAllUserFullInfos(new AllUsersGettingQuery(requesterId));
    }

    @GetMapping(path = "/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserInfoDto getUserFullInfoById(@PathVariable("userId") long userId) throws Exception
    {
        long requesterId = 0;

        return usersAccountingQueryService.getUserFullInfoById(new UserByIdGettingQuery(userId, requesterId));
    }

    @PostMapping(path = "/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserInfoDto createUser(@RequestBody UserCreatingCommand command) throws Exception
    {
        var commandResult = usersAccountingCommandService.createUser(command);

        return commandResult.userInfo();
    }

    @PutMapping(path = "/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserInfoDto updateUser(
        @PathVariable("userId") long userId, 
        @RequestBody UserUpdatingCommand command
    ) throws Exception
    {
        var commandResult = usersAccountingCommandService.updateUser(command);

        return commandResult.userInfo();
    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public UserInfoDto removeUser(@RequestBody UserRemovingCommand command) throws Exception
    {
        var commandResult = usersAccountingCommandService.removeUser(command);

        return commandResult.userInfo();
    }
}
