package org.examples.todos.domain.users.actors;

import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.roles.actors.UserRole;
import org.examples.todos.domain.users.resources.UserAddress;
import org.examples.todos.domain.users.resources.UserName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@NoArgsConstructor
public class UserInfo extends DomainEntityInfo<UUID, UserBaseInfo, UserInfo> {

	private UserAddress address;
	
	@Delegate
	public UserBaseInfo getBaseInfo()
	{
		return super.getBaseInfo();
	}
}
