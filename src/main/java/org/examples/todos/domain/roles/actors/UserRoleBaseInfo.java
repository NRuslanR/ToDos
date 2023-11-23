package org.examples.todos.domain.roles.actors;

import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityBaseInfo;
import org.examples.todos.domain.roles.resources.UserRoleClaims;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleBaseInfo extends DomainEntityBaseInfo<UUID, UserRoleBaseInfo, UserRoleInfo> 
{
    private String name;
    private UserRoleClaims claims;

    @Override
    public UserRoleInfo newFullInfoInstance() {
        
        return new UserRoleInfo();
    }

}
