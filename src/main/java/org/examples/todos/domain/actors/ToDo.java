package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.todos.ToDoWorkingRules;
import org.examples.todos.shared.utils.StringUtils;
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.SynchronizationCallbackCoordinatorNonTrackingImpl;

public class ToDo extends DomainAggregateRoot<
	UUID, 
	ToDoInfo, 
	ToDoWorkingRules, 
	User, 
	ToDo
> 
{
	public ToDo(ToDoInfo info) 
	{
		super(info);
	}

	@Override
    protected void setInfo(ToDoInfo newInfo)
    {
    	super.setInfo(newInfo);

    	setAuthor(newInfo.getAuthor());
    	setCreationDate(newInfo.getCreationDate());   	
    	setName(newInfo.getName()); 	
    	setPriority(newInfo.getPriority());
    	
    	setParentToDoId(newInfo.getParentToDoId());
    	setDescription(newInfo.getDescription());
    	setPerformingDate(newInfo.getPerformingDate());
    	setNotes(newInfo.getNotes());
    }

	private void setParentToDoId(Intention<UUID> value) 
	{	
		info.setParentToDoId(Intention.of(null));
		
		if (!Objects.isNull(value))
			setParentToDoId(value.getValue());
	}

	private void setDescription(Intention<String> value) 
	{	
		info.setDescription(Intention.of(null));
		
		if (!Objects.isNull(value))
			setDescription(value.getValue());
	}

	private void setPerformingDate(Intention<LocalDateTime> value) 
	{
		info.setPerformingDate(Intention.of(null));
		
		if (!Objects.isNull(value))
			setPerformingDate(value.getValue());
	}

	private void setNotes(Intention<ToDoNoteList> value) 
	{	
		info.setNotes(Intention.of(new ToDoNoteList()));
		
		if (!Objects.isNull(value))
    		setNotes(value.getValue());	
	}

	public UUID getParentToDoId()
	{
	    return info.getParentToDoId().getValue();
	}

    public void setParentToDoId(UUID newParentToDoId)
    {
    	ensureActorCanChangeThis();
    	
    	if (Objects.isNull(newParentToDoId))
    	{
    		throw new DomainException("Attempt to assign non-existent parent To-Do");
    	}
    	
    	if (getId().equals(newParentToDoId))
    	{
    		throw new DomainException("To-Do can't be parent for itself");
    	}
    	
        info.setParentToDoId(Intention.of(newParentToDoId));
    }
    
    public void resetParentToDo()
    {
    	ensureActorCanChangeThis();
    	
    	info.setParentToDoId(Intention.of(null));
    }

    public String getName()
    {
        return info.getName();
    }

    public void setName(String name)
    {
    	ensureActorCanChangeThis();
    	
        if (!isNameCorrect(name))
        {
            throw new DomainException("To-Do name must contain text");
        }
        
        info.setName(name);
    }

    private boolean isNameCorrect(String name) 
    {
        return StringUtils.hasText(name);
    }

    public String getDescription()
    {
        return info.getDescription().getValue();
    }

    public void setDescription(String description)
    {
    	ensureActorCanChangeThis();
    	
        info.setDescription(Intention.of(!Objects.isNull(description) ? description : ""));
    }

    public ToDoPriority getPriority()
    {
        return info.getPriority();
    }

    public void setPriorityType(ToDoPriorityType priorityType)
    {
    	ensureActorCanChangeThis();
    	
        ToDoPriority newPriority = getPriority().changeType(priorityType);

        setPriority(newPriority);
    }

    public void setPriorityValue(float value)
    {
    	ensureActorCanChangeThis();
    	
        ToDoPriority newPriority = getPriority().changeValue(value);

        setPriority(newPriority);
    }

    public void setPriority(ToDoPriority priority)
    {
    	ensureActorCanChangeThis();
    	
        if (Objects.isNull(priority))
        {
            throw new DomainException("To-Do's priority info can'be empty");
        }

        info.setPriority(priority);
    }

    public LocalDateTime getCreationDate()
    {
        return info.getCreationDate();
    }

    private void setCreationDate(LocalDateTime creationDate)
    {
    	if (!Objects.isNull(getCreationDate()))
    	{
    		throw new DomainException(
    			"To-Do \"" + getName() + 
    			"\"'s creation date is already performed"
    		);
    	}
    	
        info.setCreationDate(creationDate);
    }

    public User getAuthor()
    {
        return info.getAuthor();
    }

    private void setAuthor(User author)
    {
    	if (!Objects.isNull(getAuthor()))
    	{
    		throw new DomainException("To-Do's author can't be reassigned");
    	}
    	
        if (Objects.isNull(author))
        {
            throw new DomainException("To-Do's author info can't be empty");
        }

        info.setAuthor(author);
    }

    public ToDoNoteInfo getNoteInfo(UUID noteId)
    {
        ToDoNote note = notes().getById(noteId);

        return note.getInfo();
    }

    public void addNotes(Iterable<ToDoNote> notes)
    {
    	notes.forEach(this::addNote);
    }
    
    public void addNote(ToDoNote note)
    {
    	ensureActorCanAddNote(note);
    	
        notes().add(note.clone());    
    }

    private void ensureActorCanAddNote(ToDoNote note) 
    {	
    	workingRules()
    		.getChangingRule()
    			.ensureUserCanAssignNewToDoNote(actor(), this);
	}

	public void changeNote(ToDoNoteInfo noteInfo)
    {
    	ensureActorCanChangeThis();
    	
        ToDoNote note = notes().getById(noteInfo.getId());

        note.setInfo(noteInfo);
    }

    public void removeNote(UUID noteId)
    {
    	ensureActorCanChangeThis();
    	
        notes().removeById(noteId);   
    }
    
    public void perform()
    {
    	performForDateTime(LocalDateTime.now());
    }
    
    void performForDateTime(LocalDateTime performingDateTime)
    {
    	ensureActorCanPerformThis();
    	
    	setPerformingDate(performingDateTime);
    }

    private void ensureActorCanPerformThis() 
    {
    	workingRules().getPerformingRule().ensureToDoCanPerformedByUser(this, actor());
	}
    
	public LocalDateTime getPerformingDate()
    {
        return info.getPerformingDate().getValue();
    }
	
	public void performByOverlapping(ToDo overlappingToDo)
	{
		ensureThisPerformingCanBeOverlappedBy(overlappingToDo);
		
		setPerformingDate(overlappingToDo.getPerformingDate());
	}
	
    private void ensureThisPerformingCanBeOverlappedBy(ToDo overlappingToDo) 
    {	
		workingRules()
			.getOverlappingPerformingRule()
				.ensureToDoPerformingCanBeOverlappedByOther(this, overlappingToDo, actor());
	}

	private void setPerformingDate(LocalDateTime performingDate) throws DomainException
    {
    	LocalDateTime currentPerformingDate = getPerformingDate();
    	
        if (!Objects.isNull(currentPerformingDate))
        {
            throw new DomainException("To-Do \"" + getName() + "\" is already performed");
        }
        
        if (Objects.isNull(performingDate))
        {
        	throw new DomainException("To-Do's performing date isn't assigned");
        }
        
        if (performingDate.isBefore(info.getCreationDate()))
        {
        	throw new DomainException("To-Do \"" + getName() + "\"'s performing date can't be earlier than creation date");
        }

        info.setPerformingDate(Intention.of(performingDate));
    }
	
    public boolean isPerformed()
    {
    	return !Objects.isNull(getPerformingDate());
    }
    
    @Override
	public ToDo clone() 
    {
		var clonedToDo = super.clone();
		
		clonedToDo.setNotes(notes().clone());
						
		return clonedToDo;
	}
    
    public ToDoNoteList getNotes()
    {
    	return notes().clone();
    }
    
    private ToDoNoteList notes()
    {
    	return info.getNotes().getValue();
    }
    
    private void setNotes(ToDoNoteList newNotes)
	{
		if (Objects.isNull(newNotes))
		{
			throw new DomainException("Attempt to assign the non-existent To-Do note list");
		}
		
		var clonedNotes = newNotes.clone();
		
		info.setNotes(Intention.of(clonedNotes));
	}
}
