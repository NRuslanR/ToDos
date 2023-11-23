package org.examples.todos.application.users.accounting.commands;

import org.examples.todos.application.common.commands.ServiceCommand;
import org.examples.todos.application.users.accounting.UserCredentials;

import lombok.Data;

@Data
public class UserCreatingCommand extends ServiceCommand 
{
    private String name; 
	private String surname;
	private String patronymic;
    private UserCredentials credentials;
}
