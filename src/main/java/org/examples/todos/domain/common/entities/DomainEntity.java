package org.examples.todos.domain.common.entities;

import java.util.Objects;

import org.examples.todos.domain.common.base.DomainObject;
import org.examples.todos.domain.common.errors.DomainException;

public abstract class DomainEntity<
    Key, 
    Info extends DomainEntityInfo<Key, Info>,
    Entity extends DomainEntity<Key, Info, Entity>

> extends DomainObject<Entity> 
{
    protected Info info;

    protected DomainEntity(Info entityInfo)
    {
        info = entityInfo.newFullInfoInstance();

        setInfo(entityInfo);
    }
    
    public Info getInfo()
    {
        return (Info)info.clone();
    }

    protected void setInfo(Info newInfo)
    {
        setId(newInfo.getId());
    }

    public Key getId()
    {
        return info.getId();
    }

    private void setId(Key id)
    {
        if (Objects.isNull(id)) 
        {
            throw new DomainException("Domain entity's id can't be null");
        }
        
        this.info.setId(id);
    }

    public boolean equals(DomainEntity<Key, Info, Entity> other)
    {
        return super.equals(other);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (!(obj instanceof DomainEntity))
            return false;

        @SuppressWarnings("unchecked")
        final DomainEntity<Key, Info, Entity> otherEntity = 
        	(DomainEntity<Key, Info, Entity>)obj;

        return otherEntity.getId().getClass().equals(otherEntity.getId().getClass()) &&
            otherEntity.getId().equals(otherEntity.getId());
    }

    @Override
    public int hashCode()
    {
        return getId().hashCode();
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

	@Override
	public Entity clone() {
		
		var clonedEntity = super.clone();
		
		clonedEntity.info = info.clone();
		
		return clonedEntity;
	}
}
