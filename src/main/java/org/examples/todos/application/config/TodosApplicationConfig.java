package org.examples.todos.application.config;

import org.examples.todos.infrastructure.persistence.config.ToDosPersistenceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    RolesApplicationServicesConfig.class,
    UsersApplicationServicesConfig.class,
    TodosApplicationServicesConfig.class,
    ToDosPersistenceConfig.class
})
public class TodosApplicationConfig {

}
