package org.examples.todos.infrastructure.persistence.roles.repositories;

import java.util.UUID;

import org.examples.todos.domain.common.entities.formers.DomainEntityFormer;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.repositories.SpringJpaDomainEntityRepository;
import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;

public class SpringJpaRoleRepository 
	extends SpringJpaDomainEntityRepository<UserRole, UserRoleInfo, UUID, RoleEntity>
	implements UserRoleRepository
{

	public SpringJpaRoleRepository(
		SpringJpaRoleEntityRepository jpaRepository,
		DomainEntityInfoConverter<UserRoleInfo, RoleEntity> entityInfoConverter,
		DomainEntityFormer<UserRole, UserRoleInfo> entityFormer
	) 
	{
		super(jpaRepository, entityInfoConverter, entityFormer);
	}

}
