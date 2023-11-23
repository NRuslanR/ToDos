package org.examples.todos.application.roles.accounting.queries;

import org.examples.todos.application.common.queries.ServiceQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoleByIdGettingQuery extends ServiceQuery {

    private long roleId;

    public RoleByIdGettingQuery(long roleId, long requesterId)
    {
        setRoleId(roleId);
        setRequesterId(requesterId);
    }
}
