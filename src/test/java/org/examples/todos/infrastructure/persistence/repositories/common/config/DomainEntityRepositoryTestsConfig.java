package org.examples.todos.infrastructure.persistence.repositories.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.examples.todos.infrastructure.persistence.repositories.*")
public abstract class DomainEntityRepositoryTestsConfig
{
}
