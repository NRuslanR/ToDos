package org.examples.todos.web.api.usecases.users.accounting.controllers;

import org.examples.todos.application.users.accounting.UserDto;
import org.examples.todos.application.users.accounting.queries.UserByIdGettingQuery;
import org.examples.todos.application.users.accounting.queries.UsersAccountingQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/accounting/users")
@RequiredArgsConstructor
public class UsersAccountingApiController {
    
    private final UsersAccountingQueryService usersAccountingQueryService;

    @GetMapping(path = "/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto getUserById(@PathVariable("userId") long userId) throws Exception
    {
        var requesterId = 0;

        return usersAccountingQueryService.getUserById(new UserByIdGettingQuery(userId, requesterId));
    }
}
