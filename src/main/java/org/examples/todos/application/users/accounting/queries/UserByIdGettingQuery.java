package org.examples.todos.application.users.accounting.queries;

import org.examples.todos.application.common.queries.ServiceQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserByIdGettingQuery extends ServiceQuery {

    private long userId;

    public UserByIdGettingQuery(long userId, long requesterId)
    {
        setUserId(userId);  
        setRequesterId(requesterId);
    }

    public static UserByIdGettingQuery of(long userId, long requesterId)
    {
        return new UserByIdGettingQuery(userId, requesterId);
    }
}
