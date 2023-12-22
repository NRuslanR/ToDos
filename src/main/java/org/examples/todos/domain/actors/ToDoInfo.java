package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.resources.users.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoInfo extends DomainEntityInfo<UUID, ToDoInfo> 
{  
	private String name; 
    private ToDoPriority priority;
	private LocalDateTime creationDate;
    private User author;
    
    private Intention<UUID> parentToDoId;
	private Intention<String> description;
	private Intention<LocalDateTime> performingDate;
	private Intention<ToDoNoteList> notes;
	
	public ToDoInfo(
		UUID id, 
		String name, 
		ToDoPriority priority, 
		LocalDateTime creationDate, 
		User author,
		Intention<UUID> parentToDoId,
		Intention<String> description,
		Intention<LocalDateTime> performingDate,
		Intention<ToDoNoteList> notes
	)
	{
		this(id, name, priority, creationDate, author);
		
		setParentToDoId(parentToDoId);
		setDescription(description);
		setPerformingDate(performingDate);
		setNotes(notes);
	}
	
	public ToDoInfo(UUID id, String name, ToDoPriority priority, LocalDateTime creationDate, User author)
	{
		super(id);
		
		setName(name);
		setPriority(priority);
		setCreationDate(creationDate);
		setAuthor(author);
	}
	
	@Override
	public ToDoInfo newFullInfoInstance() 
	{
		return new ToDoInfo();
	}

	@Override
	public ToDoInfo clone() 
	{
		var clonedToDoInfo = super.clone();
		
		if (!Objects.isNull(parentToDoId))
			clonedToDoInfo.parentToDoId = Intention.of(parentToDoId.getValue());
		
		if (!Objects.isNull(description))
			clonedToDoInfo.description = Intention.of(description.getValue());
		
		if (!Objects.isNull(performingDate))
			clonedToDoInfo.performingDate = Intention.of(performingDate.getValue());
		
		if (!Objects.isNull(notes))
		{
			if (!Objects.isNull(notes.getValue()))
				clonedToDoInfo.notes = Intention.of(notes.getValue().clone());
			
			else clonedToDoInfo.notes = Intention.<ToDoNoteList>of(null);
		}
			
		return clonedToDoInfo;
	}
}
