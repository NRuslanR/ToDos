package org.examples.todos.domain.common.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class DomainEntityBaseInfo<
    Key, 
    BaseInfo extends DomainEntityBaseInfo<Key, BaseInfo, FullInfo>,
    FullInfo extends DomainEntityInfo<Key, BaseInfo, FullInfo>

> extends CloneableEntityInfo<BaseInfo>
{
    private Key id;    
    
    protected DomainEntityBaseInfo(DomainEntityBaseInfo<Key, BaseInfo, FullInfo> other)
    {
        this(other.getId());
    }

    protected DomainEntityBaseInfo(Key id)
    {
        setId(id);
    }

    public FullInfo toFullInfo()
    {
    	var fullInfo = newFullInfoInstance();
    	
    	fullInfo.setBaseInfo(clone());
    	
    	return fullInfo;
    }
    
    public abstract FullInfo newFullInfoInstance();
}
