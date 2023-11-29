package org.examples.todos.domain.resources.roles;

import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.shared.utils.StringUtils;

public class UserRole extends DomainEntity<UUID, UserRoleInfo, UserRole> {

    public UserRole(UserRoleInfo fullInfo)
    {
        super(fullInfo);
    }

    @Override
    public void setInfo(UserRoleInfo newInfo)
    {
        super.setInfo(newInfo);

        setName(newInfo.getName());
        setClaims(newInfo.getClaims());
        
        if (newInfo.getDescription().isPresent())
        	setDescription(newInfo.getDescription().get());
    }

    public String getName()
    {
        return info.getName();
    }

    public void setName(String name)
    {
        if (!isNameCorrect(name))
        {
            throw new DomainException("Role name must contain text");
        }
        
        info.setName(name);
    }

    private boolean isNameCorrect(String value) {

        return StringUtils.hasText(value);
    }

    public String getDescription()
    {
        return info.getDescription().orElse("");
    }

    public void setDescription(String description)
    {
        this.setDescription(description);
    }

    public UserRoleClaims getClaims()
    {
        return info.getClaims();
    }

    public void setClaims(UserRoleClaims claims) 
    {
        info.setClaims(claims);
    }

    public int getAllowedToDoCreationCount()
    {
        return info.getClaims().allowedToDoCreationCount();
    }

    public int getAllowedToDoNoteCreationCount()
    {
        return info.getClaims().allowedToDoNoteCreationCount();
    }

    public boolean canEditForeignToDos()
    {
        return info.getClaims().canEditForeignToDos();
    }

    public boolean canRemoveForeignToDos()
    {
        return info.getClaims().canRemoveForeignToDos();
    }
}
