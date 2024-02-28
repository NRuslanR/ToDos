package org.examples.todos.infrastructure.persistence.common;

import org.examples.todos.web.api.config.ApiConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
	ApiConfig.class,
	PersistenceTestsConfig.class
})
@WebAppConfiguration
public class PersistenceTests 
{

}
