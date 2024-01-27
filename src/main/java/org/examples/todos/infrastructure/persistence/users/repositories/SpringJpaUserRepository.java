package org.examples.todos.infrastructure.persistence.users.repositories;

import java.util.UUID;

import org.examples.todos.domain.common.entities.formers.DomainEntityFormer;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.repositories.SpringJpaDomainEntityRepository;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;

public class SpringJpaUserRepository 
	extends SpringJpaDomainEntityRepository<User, UserInfo, UUID, UserEntity>
	implements UserRepository
{

	public SpringJpaUserRepository(
		SpringJpaUserEntityRepository jpaRepository,
		DomainEntityInfoConverter<UserInfo, UserEntity> entityInfoConverter,
		DomainEntityFormer<User, UserInfo> entityFormer
	) 
	{
		super(jpaRepository, entityInfoConverter, entityFormer);
	}

}
