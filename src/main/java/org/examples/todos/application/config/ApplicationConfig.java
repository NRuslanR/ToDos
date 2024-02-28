package org.examples.todos.application.config;

import org.examples.todos.application.config.common.ApplicationComponentScan;
import org.examples.todos.domain.config.DomainConfig;
import org.examples.todos.infrastructure.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ApplicationComponentScan
@Import({
	DomainConfig.class,
    PersistenceConfig.class
})
public class ApplicationConfig {

}
