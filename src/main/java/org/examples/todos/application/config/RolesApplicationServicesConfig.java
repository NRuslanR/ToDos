package org.examples.todos.application.config;

import org.examples.todos.application.roles.accounting.commands.RolesAccountingCommandService;
import org.examples.todos.application.roles.accounting.commands.standard.StandardRolesAccountingCommandService;
import org.examples.todos.application.roles.accounting.queries.RolesAccountingQueryService;
import org.examples.todos.application.roles.accounting.queries.standard.StandardRolesAccountingQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesApplicationServicesConfig 
{    
    @Bean
    public RolesAccountingQueryService rolesAccountingQueryService()
    {
        return new StandardRolesAccountingQueryService();
    }

    @Bean
    public RolesAccountingCommandService rolesAccountingCommandService()
    {
        return new StandardRolesAccountingCommandService();
    }
}
