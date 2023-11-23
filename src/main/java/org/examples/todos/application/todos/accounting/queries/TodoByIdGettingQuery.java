package org.examples.todos.application.todos.accounting.queries;

import org.examples.todos.application.common.queries.ServiceQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TodoByIdGettingQuery extends ServiceQuery {
	
	private long toDoId;

	public TodoByIdGettingQuery(long toDoId, long requesterId)
	{
		setToDoId(toDoId);
		setRequesterId(requesterId);
	}

	public static TodoByIdGettingQuery of (long toDoId, long requesterId)
	{
		return new TodoByIdGettingQuery(toDoId, requesterId);
	}
}
