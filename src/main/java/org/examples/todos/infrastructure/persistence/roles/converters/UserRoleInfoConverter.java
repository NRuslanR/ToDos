package org.examples.todos.infrastructure.persistence.roles.converters;

import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;

public interface UserRoleInfoConverter extends DomainEntityInfoConverter<UserRoleInfo, RoleEntity>
{

}
