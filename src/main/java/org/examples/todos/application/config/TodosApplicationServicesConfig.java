package org.examples.todos.application.config;

import org.examples.todos.application.todos.accounting.commands.ToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.commands.standard.StandardToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.queries.ToDosAccountingQueryService;
import org.examples.todos.application.todos.accounting.queries.standard.StandardToDosAccountingQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodosApplicationServicesConfig 
{
    @Bean
    public ToDosAccountingQueryService toDosAccountingQueryService()
    {
        return new StandardToDosAccountingQueryService();
    }

    @Bean
    public ToDosAccountingCommandService toDosAccountingCommandService()
    {
        return new StandardToDosAccountingCommandService();
    }
}
