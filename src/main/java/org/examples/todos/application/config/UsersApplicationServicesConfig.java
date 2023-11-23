package org.examples.todos.application.config;

import org.examples.todos.application.users.accounting.commands.UsersAccountingCommandService;
import org.examples.todos.application.users.accounting.commands.standard.StandardUsersAccountingCommandService;
import org.examples.todos.application.users.accounting.queries.UsersAccountingQueryService;
import org.examples.todos.application.users.accounting.queries.standard.StandardUsersAccountingQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersApplicationServicesConfig 
{    
    @Bean
    public UsersAccountingQueryService usersAccountingQueryService()
    {
        return new StandardUsersAccountingQueryService();
    }

    @Bean
    public UsersAccountingCommandService usersAccountingCommandService()
    {
        return new StandardUsersAccountingCommandService();
    }
}
