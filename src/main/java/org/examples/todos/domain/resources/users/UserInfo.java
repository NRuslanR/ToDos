package org.examples.todos.domain.resources.users;

import java.util.Optional;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.resources.roles.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo extends DomainEntityInfo<UUID, UserInfo> {
    
    private UserName name;
    private UserRole role;
    private Optional<UserAddress> address;
    
	@Override
	public UserInfo newFullInfoInstance() {
		
		return new UserInfo();
	}

	@Override
	public UserInfo clone() {
		
		var clonedUserInfo = super.clone();
		
		clonedUserInfo.address = Optional.ofNullable(address.orElse(null));
		
		return clonedUserInfo;
	}
}
