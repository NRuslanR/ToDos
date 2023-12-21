package org.examples.todos.domain.resources.roles;

import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.entities.DomainEntityInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleInfo extends DomainEntityInfo<UUID, UserRoleInfo> 
{
    private String name;
    private UserRoleClaims claims;
    private Intention<String> description;

    public UserRoleInfo(UUID id, String name, String description, UserRoleClaims claims) 
    {
    	this(id, name, claims);
    	
    	setDescription(Intention.of(description));
    }
    
    public UserRoleInfo(UUID id, String name, UserRoleClaims claims) 
    {
    	super(id);
    	
    	setName(name);
    	setClaims(claims);
    }
    
    @Override
    public UserRoleInfo newFullInfoInstance() 
    {        
        return new UserRoleInfo();
    }

	@Override
	public UserRoleInfo clone() 
	{		
		var clonedUserRoleInfo = super.clone();
		
		if (!Objects.isNull(description))
			clonedUserRoleInfo.description = Intention.of(description.getValue());
		
		return clonedUserRoleInfo;
	}
}
