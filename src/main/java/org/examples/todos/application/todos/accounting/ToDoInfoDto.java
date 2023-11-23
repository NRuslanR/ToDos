package org.examples.todos.application.todos.accounting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoInfoDto
{
	private ToDoDto toDo;
	private ToDoAccessRightsDto accessRights;
}
