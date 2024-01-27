package org.examples.todos.domain.config.services.performing;

import org.examples.todos.domain.config.common.DomainComponentScan;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;
import org.examples.todos.domain.operations.todos.performing.StandardToDoOrdinaryPerformingService;
import org.examples.todos.domain.operations.todos.performing.StandardToDoOverlappingPerformingService;
import org.examples.todos.domain.operations.todos.performing.ToDoOrdinaryPerformingService;
import org.examples.todos.domain.operations.todos.performing.ToDoOverlappingPerformingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@DomainComponentScan
@Profile({ "dev", "prod" })
public class StandardDomainEntityPerformingConfig 
{
	private final ToDoFinder toDoFinder;
	
	@Autowired
	public StandardDomainEntityPerformingConfig(ToDoFinder toDoFinder)
	{
		this.toDoFinder = toDoFinder;
	}

	@Bean
	public ToDoOrdinaryPerformingService toDoOrdinaryPerformingService()
	{
		return new StandardToDoOrdinaryPerformingService();
	}
	
	@Bean
	public ToDoOverlappingPerformingService toDoOverlappingPerformingService()
	{
		return new StandardToDoOverlappingPerformingService(toDoFinder);
	}
}
