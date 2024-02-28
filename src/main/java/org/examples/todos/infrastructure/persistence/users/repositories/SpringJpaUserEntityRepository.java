package org.examples.todos.infrastructure.persistence.users.repositories;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaUserEntityRepository extends JpaRepository<UserEntity, UUID>
{

}
