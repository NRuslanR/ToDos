package org.examples.todos.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:web/h2-console-props.properties")
public class H2ConsoleConfig 
{
	
}
