package org.examples.todos.domain.resources.roles;

import java.util.Optional;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleInfo extends DomainEntityInfo<UUID, UserRoleInfo> 
{
    private String name;
    private UserRoleClaims claims;
    private Optional<String> description;
    
    @Override
    public UserRoleInfo newFullInfoInstance() {
        
        return new UserRoleInfo();
    }

}
