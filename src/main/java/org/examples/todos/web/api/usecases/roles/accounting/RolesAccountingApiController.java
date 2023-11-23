package org.examples.todos.web.api.usecases.roles.accounting;

import org.examples.todos.application.roles.accounting.RoleDto;
import org.examples.todos.application.roles.accounting.commands.RoleCreatingCommand;
import org.examples.todos.application.roles.accounting.commands.RoleRemovingCommand;
import org.examples.todos.application.roles.accounting.commands.RoleUpdatingCommand;
import org.examples.todos.application.roles.accounting.commands.RolesAccountingCommandService;
import org.examples.todos.application.roles.accounting.queries.AllRolesGettingQuery;
import org.examples.todos.application.roles.accounting.queries.RoleByIdGettingQuery;
import org.examples.todos.application.roles.accounting.queries.RolesAccountingQueryService;
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
@RequestMapping(path = "/api/accounting/roles")
@RequiredArgsConstructor
public class RolesAccountingApiController {

    private final RolesAccountingQueryService rolesAccountingQueryService;
    private final RolesAccountingCommandService rolesAccountingCommandService;
    
    @GetMapping(path = "/")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<RoleDto> getAllRoles() throws Exception
    {
        long requesterId = 0;

        return rolesAccountingQueryService.getAllRoles(new AllRolesGettingQuery(requesterId));
    }

    @GetMapping(path = "/{roleId}")
    @ResponseStatus(code = HttpStatus.OK)
    public RoleDto getRoleById(@PathVariable("roleId") long roleId) throws Exception
    {
        long requesterId = 0;

        return rolesAccountingQueryService.getRoleById(new RoleByIdGettingQuery(roleId, requesterId));
    }

    @PostMapping(path = "/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoleDto createRole(@RequestBody RoleCreatingCommand command) throws Exception
    {
        var commandResult = rolesAccountingCommandService.createRole(command);

        return commandResult.role();
    }

    @PutMapping(path = "/{roleId}")
    @ResponseStatus(code = HttpStatus.OK)
    public RoleDto updateRole(
        @PathVariable("roleId") long roleId, 
        @RequestBody RoleUpdatingCommand command) throws Exception
    {
        var commandResult = rolesAccountingCommandService.updateRole(command);

        return commandResult.role();
    }

    @DeleteMapping(path = "/{roleId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public RoleDto removeRole(
        @PathVariable("roleId") long roleId) throws Exception
    {
        long initiatorId = 0;

        var commandResult = 
            rolesAccountingCommandService.removeRole(
                new RoleRemovingCommand(roleId, initiatorId)
            );

        return commandResult.role();
    }
}
