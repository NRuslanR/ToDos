package org.examples.todos.domain.config.services.forming;

import org.examples.todos.domain.config.common.DomainComponentScan;
import org.examples.todos.domain.operations.todos.forming.StandardToDoFormer;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.roles.StandardUserRoleFormer;
import org.examples.todos.domain.resources.roles.UserRoleFormer;
import org.examples.todos.domain.resources.users.StandardUserFormer;
import org.examples.todos.domain.resources.users.UserFormer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@DomainComponentScan
@Profile({ "prod", "dev" })
public class StandardDomainEntityFormingConfig 
{	
	@Bean
	public UserRoleFormer userRoleFormer()
	{
		return new StandardUserRoleFormer();
	}
	
	@Bean 
	public UserFormer userFormer()
	{
		return new StandardUserFormer();
	}
	
	@Bean
	public ToDoFormer toDoFormer()
	{
		return new StandardToDoFormer();
	}
}
