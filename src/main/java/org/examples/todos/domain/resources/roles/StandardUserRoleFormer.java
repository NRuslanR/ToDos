package org.examples.todos.domain.resources.roles;

import org.examples.todos.domain.common.errors.DomainException;

public class StandardUserRoleFormer implements UserRoleFormer
{
	@Override
	public UserRole formEntity(UserRoleInfo info) throws DomainException 
	{	
		return new UserRole(info);	
	}
}
