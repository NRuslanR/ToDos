package org.examples.todos.infrastructure.persistence.roles.repositories;

import java.util.UUID;

import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.infrastructure.persistence.common.repositories.DomainEntityRepository;

public interface UserRoleRepository extends DomainEntityRepository<UserRole, UUID> 
{

}
