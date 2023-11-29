package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.resources.users.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoInfo extends DomainEntityInfo<UUID, ToDoInfo> {
    
	private String name; 
    private ToDoPriority priority;
	private LocalDateTime creationDate;
    private User author;
    
    private Optional<UUID> parentToDoId;
	private Optional<String> description;
	private Optional<LocalDateTime> performingDate;
	
	@Override
	public ToDoInfo newFullInfoInstance() {
		
		return new ToDoInfo();
	}

}
