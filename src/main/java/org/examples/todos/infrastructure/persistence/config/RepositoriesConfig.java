package org.examples.todos.infrastructure.persistence.config;

import org.examples.todos.domain.config.common.DomainComponentScan;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.roles.UserRoleFormer;
import org.examples.todos.domain.resources.users.UserFormer;
import org.examples.todos.infrastructure.persistence.config.common.PersistenceComponentScan;
import org.examples.todos.infrastructure.persistence.roles.converters.DelegatingUserRoleInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.converters.ModelMapperUserRoleInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.converters.UserRoleInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.repositories.SpringJpaRoleEntityRepository;
import org.examples.todos.infrastructure.persistence.roles.repositories.SpringJpaRoleRepository;
import org.examples.todos.infrastructure.persistence.roles.repositories.UserRoleRepository;
import org.examples.todos.infrastructure.persistence.todos.converters.DelegatingToDoInfoConverter;
import org.examples.todos.infrastructure.persistence.todos.converters.ModelMapperToDoInfoConverter;
import org.examples.todos.infrastructure.persistence.todos.converters.ToDoInfoConverter;
import org.examples.todos.infrastructure.persistence.todos.repositories.SpringJpaToDoEntityRepository;
import org.examples.todos.infrastructure.persistence.todos.repositories.SpringJpaToDoRepository;
import org.examples.todos.infrastructure.persistence.todos.repositories.ToDoRepository;
import org.examples.todos.infrastructure.persistence.users.converters.DelegatingUserInfoConverter;
import org.examples.todos.infrastructure.persistence.users.converters.ModelMapperUserInfoConverter;
import org.examples.todos.infrastructure.persistence.users.converters.UserInfoConverter;
import org.examples.todos.infrastructure.persistence.users.repositories.SpringJpaUserEntityRepository;
import org.examples.todos.infrastructure.persistence.users.repositories.SpringJpaUserRepository;
import org.examples.todos.infrastructure.persistence.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "org.examples.todos.infrastructure.persistence.*" })
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
	public UserRoleInfoConverter userRoleInfoConverter()
	{
		return new DelegatingUserRoleInfoConverter(
			new ModelMapperUserRoleInfoConverter(modelMapper())
		);
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
			userRoleInfoConverter(),
			userRoleFormer
		);
	}
	
	@Bean
	public UserInfoConverter userInfoConverter()
	{
		return new DelegatingUserInfoConverter(
			new ModelMapperUserInfoConverter(modelMapper())
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
			userInfoConverter(), 
			userFormer		
		);
	}
	
	@Bean
	public ToDoInfoConverter toDoInfoConverter()
	{
		return new DelegatingToDoInfoConverter(
			new ModelMapperToDoInfoConverter(modelMapper())
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
			toDoInfoConverter(), 
			toDoFormer
		);
	}
}
