package org.examples.todos.domain.users.actors;

import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityBaseInfo;
import org.examples.todos.domain.roles.actors.UserRole;
import org.examples.todos.domain.users.resources.UserAddress;
import org.examples.todos.domain.users.resources.UserName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBaseInfo extends DomainEntityBaseInfo<UUID, UserBaseInfo, UserInfo> {
    
    private UserName name;
    private UserRole role;
    
	@Override
	public UserInfo newFullInfoInstance() {
		
		return new UserInfo();
	}
}
