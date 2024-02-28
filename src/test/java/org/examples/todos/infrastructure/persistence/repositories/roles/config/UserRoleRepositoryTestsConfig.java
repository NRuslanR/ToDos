package org.examples.todos.infrastructure.persistence.repositories.roles.config;

import org.examples.todos.infrastructure.persistence.repositories.common.config.DomainEntityRepositoryTestsConfig;
import org.examples.todos.infrastructure.persistence.repositories.roles.DelegatingUserRoleStoreInitializer;
import org.examples.todos.infrastructure.persistence.repositories.roles.SpringJpaUserRoleStoreInitializer;
import org.examples.todos.infrastructure.persistence.repositories.roles.UserRoleStoreInitializer;
import org.examples.todos.infrastructure.persistence.roles.converters.UserRoleInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.repositories.SpringJpaRoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class UserRoleRepositoryTestsConfig extends DomainEntityRepositoryTestsConfig
{
	@Bean
	@Autowired
	public UserRoleStoreInitializer springJpaUserRoleStoreInitializer(
			SpringJpaRoleEntityRepository springJpaRoleEntityRepository,
			UserRoleInfoConverter domainEntityInfoConverter
	)
	{
		return new DelegatingUserRoleStoreInitializer(
			new SpringJpaUserRoleStoreInitializer(
				springJpaRoleEntityRepository, 
				domainEntityInfoConverter
			)
		);
	}
}
