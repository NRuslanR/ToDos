package org.examples.todos.application.todos.accounting.queries;

import org.examples.todos.application.common.queries.ServiceQuery;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewToDoInstanceGettingQuery extends ServiceQuery {

    public NewToDoInstanceGettingQuery(long requesterId)
    {
        super(requesterId);
    }
}
