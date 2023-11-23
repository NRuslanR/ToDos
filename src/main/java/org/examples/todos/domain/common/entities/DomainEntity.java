package org.examples.todos.domain.common.entities;

import java.util.Objects;

import org.examples.todos.domain.common.errors.DomainException;

// refactor for equals, law of demeter
public abstract class DomainEntity<
    Key, 
    BaseInfo extends DomainEntityBaseInfo<Key, BaseInfo, FullInfo>,
    FullInfo extends DomainEntityInfo<Key, BaseInfo, FullInfo>,
    Entity extends CloneableEntity<Entity>

> extends CloneableEntity<Entity> 
{
    
    protected FullInfo info;

    protected DomainEntity(BaseInfo baseInfo)
    {
        this(baseInfo.toFullInfo());
    }

    protected DomainEntity(FullInfo fullInfo)
    {
        info = fullInfo.getBaseInfo().newFullInfoInstance();

        setInfo(fullInfo);
    }
    
    public FullInfo getInfo()
    {
        return (FullInfo)info.clone();
    }

    public void setInfo(FullInfo newInfo)
    {
        setId(newInfo.getBaseInfo().getId());
    }

    public Key getId()
    {
        return info.getBaseInfo().getId();
    }

    private void setId(Key id)
    {
        if (Objects.isNull(id)) 
        {
            throw new DomainException("Domain entity's id can't be null");
        }
        
        this.info.getBaseInfo().setId(id);
    }

    public boolean equals(DomainEntity<Key, BaseInfo, FullInfo, Entity> other)
    {
        return super.equals(other);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (!(obj instanceof DomainEntity))
            return false;

        @SuppressWarnings("unchecked")
        final DomainEntity<Key, BaseInfo, FullInfo, Entity> otherEntity = 
        	(DomainEntity<Key, BaseInfo, FullInfo, Entity>)obj;

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
}
