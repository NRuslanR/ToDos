package org.examples.todos.domain.common.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class DomainEntityInfo<
    Key, 
    Info extends DomainEntityInfo<Key, Info>

> extends CloneableEntityInfo<Info>
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
