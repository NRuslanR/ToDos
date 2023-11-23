package org.examples.todos.domain.todos.resources;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityBaseInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoNoteBaseInfo extends DomainEntityBaseInfo<UUID, ToDoNoteBaseInfo, ToDoNoteInfo>
{
    private String name;
    private String text;
    private LocalDateTime createdAt;
    
	@Override
	public ToDoNoteInfo newFullInfoInstance() {
		
		return new ToDoNoteInfo();
	}
}