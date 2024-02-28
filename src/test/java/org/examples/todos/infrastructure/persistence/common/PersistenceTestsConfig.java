package org.examples.todos.infrastructure.persistence.common;

import org.examples.todos.infrastructure.persistence.repositories.roles.config.UserRoleRepositoryTestsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
	UserRoleRepositoryTestsConfig.class
})
public class PersistenceTestsConfig {

}
