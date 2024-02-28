package org.examples.todos.domain.common.entities;

import org.examples.todos.domain.common.base.DomainObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class DomainEntityInfo<
    Key, 
    Info extends DomainEntityInfo<Key, Info>

> extends DomainObject<Info>
{
    private Key id;    
    
    protected DomainEntityInfo(DomainEntityInfo<Key, Info> other)
    {
        this(other.getId());
    }

    protected DomainEntityInfo(Key id)
    {
        setId(id);
    }

	public abstract Info newFullInfoInstance();
}
