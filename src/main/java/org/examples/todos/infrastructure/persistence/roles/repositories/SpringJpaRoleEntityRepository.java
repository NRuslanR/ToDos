package org.examples.todos.infrastructure.persistence.roles.repositories;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaRoleEntityRepository extends JpaRepository<RoleEntity, UUID>
{

}
