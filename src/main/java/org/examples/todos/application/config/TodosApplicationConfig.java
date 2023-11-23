package org.examples.todos.application.config;

import org.examples.todos.infrastructure.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    RolesApplicationServicesConfig.class,
    UsersApplicationServicesConfig.class,
    TodosApplicationServicesConfig.class,
    PersistenceConfig.class
})
public class TodosApplicationConfig {

}
