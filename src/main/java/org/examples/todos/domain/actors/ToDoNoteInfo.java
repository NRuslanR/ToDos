package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoNoteInfo extends DomainEntityInfo<UUID, ToDoNoteInfo>
{
    private String name;
    private String text;
    private LocalDateTime createdAt;
    
	@Override
	public ToDoNoteInfo newFullInfoInstance() {
		
		return new ToDoNoteInfo();
	}
}