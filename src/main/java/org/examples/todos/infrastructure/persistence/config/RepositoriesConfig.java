package org.examples.todos.infrastructure.persistence.config;

import org.examples.todos.domain.config.common.DomainComponentScan;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.roles.UserRoleFormer;
import org.examples.todos.domain.resources.users.UserFormer;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.converters.ModelMapperDomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.config.common.PersistenceComponentScan;
import org.examples.todos.infrastructure.persistence.roles.repositories.SpringJpaRoleEntityRepository;
import org.examples.todos.infrastructure.persistence.roles.repositories.SpringJpaRoleRepository;
import org.examples.todos.infrastructure.persistence.roles.repositories.UserRoleRepository;
import org.examples.todos.infrastructure.persistence.todos.repositories.SpringJpaToDoEntityRepository;
import org.examples.todos.infrastructure.persistence.todos.repositories.SpringJpaToDoRepository;
import org.examples.todos.infrastructure.persistence.todos.repositories.ToDoRepository;
import org.examples.todos.infrastructure.persistence.users.repositories.SpringJpaUserEntityRepository;
import org.examples.todos.infrastructure.persistence.users.repositories.SpringJpaUserRepository;
import org.examples.todos.infrastructure.persistence.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@PersistenceComponentScan
@DomainComponentScan
@SuppressWarnings("unchecked")
public class RepositoriesConfig 
{
	@Bean(name = "entityInfoModelMapper")	
	public ModelMapper modelMapper()
	{
		var mapper = new ModelMapper();
		
		ModelMapperDomainEntityInfoMappingConfig.configure(mapper);
		
		return mapper;
	}
	
	@Bean
	public DomainEntityInfoConverter domainEntityInfoConverter()
	{
		return new ModelMapperDomainEntityInfoConverter(modelMapper());
	}
	
	@Bean
	@Autowired
	public UserRoleRepository userRoleRepository(
		SpringJpaRoleEntityRepository roleEntityRepository,
		UserRoleFormer userRoleFormer
	)
	{
		return new SpringJpaRoleRepository(
			roleEntityRepository, 
			domainEntityInfoConverter(),
			userRoleFormer
		);
	}
	
	@Bean
	@Autowired
	public UserRepository userRepository(
		SpringJpaUserEntityRepository userEntityRepository,
		UserFormer userFormer
	)
	{
		return new SpringJpaUserRepository(
			userEntityRepository, 
			domainEntityInfoConverter(), 
			userFormer		
		);
	}
	
	@Bean
	@Autowired
	public ToDoRepository toDoRepository(
		SpringJpaToDoEntityRepository toDoEntityRepository,
		ToDoFormer toDoFormer
	)
	{
		return new SpringJpaToDoRepository(
			toDoEntityRepository, 
			domainEntityInfoConverter(), 
			toDoFormer
		);
	}
}
