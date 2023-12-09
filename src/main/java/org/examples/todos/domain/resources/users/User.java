package org.examples.todos.domain.resources.users;

import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.roles.UserRole;

public class User extends DomainEntity<UUID, UserInfo, User> 
{
	public User(UserInfo info)
	{
		super(info);
	}
	
	@Override
	protected void setInfo(UserInfo newInfo)
	{
		super.setInfo(newInfo);
		
		setName(newInfo.getName());
		setRole(newInfo.getRole());
		
		setAddress(newInfo.getAddress());
	}

    private void setAddress(Intention<UserAddress> address) 
    {	
    	if (!Objects.isNull(address))
    		setAddress(address.getValue());
		
    	else info.setAddress(Intention.of(null));
	}

	public String getFirstName()
    {
        return info.getName().firstName();
    }

    public void setFirstName(String firstName)
    {
        setName(info.getName().changeFirstName(firstName));
    }

    public String getMiddleName()
    {
        return info.getName().middleName();
    }

    public void setMiddleName(String middleName)
    {
        setName(info.getName().changeMiddleName(middleName));
    }

    public String getLastName()
    {
        return info.getName().lastName();
    }

    public void setLastName(String lastName)
    {
        setName(info.getName().changeLastName(lastName));
    }

    public String getFullName()
    {
        return info.getName().fullName();
    }

    private void setName(UserName name) 
    {
        if (Objects.isNull(name))
        {
            throw new DomainException("User's name info can'be empty");
        }

        info.setName(name);
    }

    public UserAddress getAddress()
    {
        return info.getAddress().getValue();
    }
    
    public void setAddress(UserAddress address) 
    {
        if (Objects.isNull(address))
        {
            throw new DomainException("User's address info can'be empty");
        }

        info.setAddress(Intention.of(address));
    }

    public UserRole getRole()
    {
        return info.getRole();
    }

    public void setRole(UserRole role) 
    {
        if (Objects.isNull(role))
        {
            throw new DomainException("User's role info can'be empty");
        }

        info.setRole(role);
    }

    public int getAllowedToDoCreationCount()
    {
        return info.getRole().getAllowedToDoCreationCount();
    }

    public int getAllowedToDoNoteCreationCount()
    {
        return info.getRole().getAllowedToDoNoteCreationCount();
    }

    public boolean canEditForeignToDos()
    {
        return info.getRole().canEditForeignToDos();
    }

    public boolean canRemoveForeignToDos()
    {
        return info.getRole().canRemoveForeignToDos();
    }
}
