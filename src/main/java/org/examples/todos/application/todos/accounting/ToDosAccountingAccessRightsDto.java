package org.examples.todos.application.todos.accounting;

public record ToDosAccountingAccessRightsDto(
	boolean canViewTodos,
	boolean canCreateToDos
) 
{
	public boolean allAccessRightsAbsent()
	{
		return !anyAccessRightsPresent();
	}

	private boolean anyAccessRightsPresent() {
		
		return
				   canViewTodos 
				|| canCreateToDos;
	}
}
