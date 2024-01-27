package org.examples.todos.domain.config.services.operations;

import org.examples.todos.domain.config.common.DomainComponentScan;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;
import org.examples.todos.domain.operations.todos.forming.StandardToDoCreationService;
import org.examples.todos.domain.operations.todos.forming.StandardToDoParentAssigningService;
import org.examples.todos.domain.operations.todos.forming.ToDoCreationService;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.operations.todos.forming.ToDoParentAssigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@DomainComponentScan
@Profile({ "prod", "dev" })
public class StandardDomainEntityOperationsConfig 
{
	private final ToDoFinder toDoFinder;
	private final ToDoFormer toDoFormer;
	
	@Autowired
	public StandardDomainEntityOperationsConfig(ToDoFinder toDoFinder, ToDoFormer toDoFormer)
	{
		this.toDoFinder = toDoFinder;
		this.toDoFormer = toDoFormer;
	}
	
	@Bean
	public ToDoCreationService toDoCreationService()
	{
		return new StandardToDoCreationService(toDoFinder, toDoFormer);
	}
	
	@Bean
	public ToDoParentAssigningService toDoParentAssigningService()
	{
		return new StandardToDoParentAssigningService(toDoFinder);
	}
}
