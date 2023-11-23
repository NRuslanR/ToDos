package org.examples.todos.domain.common.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Delegate;

@Data
@NoArgsConstructor
public abstract class DomainEntityInfo<
    Key, 
    BaseInfo extends DomainEntityBaseInfo<Key, BaseInfo, FullInfo>,
    FullInfo extends DomainEntityInfo<Key, BaseInfo, FullInfo>

> extends CloneableEntityInfo<FullInfo>
{
    @NonNull
    private BaseInfo baseInfo;
}
