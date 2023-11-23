package org.examples.todos.domain.roles.actors;

import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@NoArgsConstructor
public class UserRoleInfo extends DomainEntityInfo<UUID, UserRoleBaseInfo, UserRoleInfo>
{
    private String description;

    @Delegate
    public UserRoleBaseInfo getBaseInfo()
    {
        return super.getBaseInfo();
    }
}

