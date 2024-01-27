package org.examples.todos.domain.resources.users;

import org.examples.todos.domain.common.errors.DomainException;

public class StandardUserFormer implements UserFormer
{
	@Override
	public User formEntity(UserInfo info) throws DomainException 
	{
		return new User(info);
	}
}
