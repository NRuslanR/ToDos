package org.examples.todos.application.users.accounting.commands;

import org.examples.todos.application.common.commands.ServiceCommand;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserRemovingCommand extends ServiceCommand 
{
    private long userId;

    public UserRemovingCommand(long userId, long initiatorId)
    {
        setUserId(userId);
        setInitiatorId(initiatorId);
    }
}
