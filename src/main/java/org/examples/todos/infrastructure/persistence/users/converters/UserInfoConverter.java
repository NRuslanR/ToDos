package org.examples.todos.infrastructure.persistence.users.converters;

import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;

public interface UserInfoConverter extends DomainEntityInfoConverter<UserInfo, UserEntity> 
{

}
