package org.examples.todos.application.users.accounting.queries;

import org.examples.todos.application.common.queries.ServiceQuery;

public class AllUsersGettingQuery extends ServiceQuery {

    public AllUsersGettingQuery(long requesterId)
    {
        setRequesterId(requesterId);
    }
}
