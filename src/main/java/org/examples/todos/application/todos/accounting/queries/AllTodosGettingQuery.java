package org.examples.todos.application.todos.accounting.queries;

import org.examples.todos.application.common.queries.ServiceQuery;

import lombok.Data;

@Data
public class AllTodosGettingQuery extends ServiceQuery
{	
	public AllTodosGettingQuery(long requesterId)
    {
        super(requesterId);
    }
}
