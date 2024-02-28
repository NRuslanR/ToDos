package org.examples.todos.application.todos.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoInfoDto
{
	private ToDoDto toDo;
	private ToDoAccessRightsDto accessRights;
}
