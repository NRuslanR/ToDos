package org.examples.todos.application.roles.accounting.commands;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoleCreatingCommand extends ServiceCommand {

    private String name;
    private String description;

}
