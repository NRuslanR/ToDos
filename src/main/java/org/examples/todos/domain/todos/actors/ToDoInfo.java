package org.examples.todos.domain.todos.actors;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@NoArgsConstructor
public class ToDoInfo extends DomainEntityInfo<UUID, ToDoBaseInfo, ToDoInfo> {

	private UUID parentToDoId;
	private String description;
	private LocalDateTime performingDate;
	
	@Delegate
	public ToDoBaseInfo getBaseInfo()
	{
		return super.getBaseInfo();
	}
}
