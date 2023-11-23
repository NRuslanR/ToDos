package org.examples.todos.application.todos.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoAccessRightsDto
{
	private boolean canBeViewed;
	private boolean canBeChanged;
	private boolean canBeRemoved;
	private boolean canBePerformed;
	
	public boolean allAccessRightsAbsent()
	{
		return !anyAccessRightsPresent();
	}

	private boolean anyAccessRightsPresent() {
		
		return 
				   canBeViewed
				|| canBeChanged
				|| canBeRemoved
				|| canBePerformed;
	}
}
