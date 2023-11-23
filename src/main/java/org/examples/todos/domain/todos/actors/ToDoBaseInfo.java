package org.examples.todos.domain.todos.actors;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityBaseInfo;
import org.examples.todos.domain.todos.resources.ToDoNoteBaseInfo;
import org.examples.todos.domain.todos.resources.ToDoNoteInfo;
import org.examples.todos.domain.todos.resources.ToDoPriority;
import org.examples.todos.domain.users.actors.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoBaseInfo extends DomainEntityBaseInfo<UUID, ToDoBaseInfo, ToDoInfo> {
    
	private String name; 
    private ToDoPriority priority;
	private LocalDateTime creationDate;
    private User author;
    
	@Override
	public ToDoInfo newFullInfoInstance() {
		
		return new ToDoInfo();
	}

}
