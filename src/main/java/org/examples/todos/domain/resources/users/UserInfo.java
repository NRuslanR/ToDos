package org.examples.todos.domain.resources.users;

import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.resources.roles.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo extends DomainEntityInfo<UUID, UserInfo> 
{
    private UserName name;
    private UserRole role;
    private Intention<UserAddress> address;
    
	@Override
	public UserInfo newFullInfoInstance() 
	{	
		return new UserInfo();
	}

	@Override
	public UserInfo clone() 
	{
		var clonedUserInfo = super.clone();
		
		if (!Objects.isNull(address))
			clonedUserInfo.address = Intention.of(address.getValue());
		
		return clonedUserInfo;
	}
}
