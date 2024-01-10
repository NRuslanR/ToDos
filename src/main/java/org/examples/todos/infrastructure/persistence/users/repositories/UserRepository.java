package org.examples.todos.infrastructure.persistence.users.repositories;

import java.util.UUID;

import org.examples.todos.domain.resources.users.User;
import org.examples.todos.infrastructure.persistence.common.repositories.DomainEntityRepository;

public interface UserRepository extends DomainEntityRepository<User, UUID>
{
    
}
