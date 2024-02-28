package org.examples.todos.infrastructure.persistence.repositories.roles;

import java.util.UUID;

import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.infrastructure.persistence.repositories.common.SpringJpaDomainEntityStoreInitializer;
import org.examples.todos.infrastructure.persistence.roles.converters.UserRoleInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.repositories.SpringJpaRoleEntityRepository;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public class SpringJpaUserRoleStoreInitializer 
	extends SpringJpaDomainEntityStoreInitializer<UserRole, UUID, UserEntity> 
{

	public SpringJpaUserRoleStoreInitializer(
			SpringJpaRoleEntityRepository jpaRepository,
			UserRoleInfoConverter domainEntityInfoConverter
	) 
	{
		super((JpaRepository)jpaRepository, domainEntityInfoConverter);
	}

}
