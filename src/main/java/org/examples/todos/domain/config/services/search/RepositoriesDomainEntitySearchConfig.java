package org.examples.todos.domain.config.services.search;

import org.examples.todos.domain.config.common.DomainComponentScan;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;
import org.examples.todos.infrastructure.persistence.config.RepositoriesConfig;
import org.examples.todos.infrastructure.persistence.todos.repositories.RepositoryToDoFinder;
import org.examples.todos.infrastructure.persistence.todos.repositories.ToDoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@DomainComponentScan
@Profile({ "prod", "dev" })
@Import({ RepositoriesConfig.class })
public class RepositoriesDomainEntitySearchConfig 
{
	@Bean
	public ToDoFinder toDoFinder(ToDoRepository toDoRepository)
	{
		return new RepositoryToDoFinder(toDoRepository);
	}
}
