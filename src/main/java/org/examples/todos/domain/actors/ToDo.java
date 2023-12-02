package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.resources.users.User;
import org.examples.todos.domain.rules.todos.ToDoWorkingRules;
import org.examples.todos.shared.utils.StringUtils;

public class ToDo extends DomainAggregateRoot<
	UUID, 
	ToDoInfo, 
	ToDoWorkingRules, 
	User, 
	ToDo
> 
{
	private ToDoNoteList notes = new ToDoNoteList();

	public ToDo(ToDoInfo Info, ToDoWorkingRules workingRules, User actor) {
		super(Info, workingRules, actor);
	}

	@Override
    protected void setInfo(ToDoInfo newInfo)
    {
    	super.setInfo(newInfo);

    	setAuthor(newInfo.getAuthor());
    	setCreationDate(newInfo.getCreationDate());   	
    	setName(newInfo.getName()); 	
    	setPriority(newInfo.getPriority());
    	
    	// refactor: remove
    	if (newInfo.getParentToDoId().isPresent())
    		setParentToDoId(newInfo.getParentToDoId().get());
    	
    	if (newInfo.getDescription().isPresent())
    		setDescription(newInfo.getDescription().get());
    	
    	if (newInfo.getPerformingDate().isPresent())
    		setPerformingDate(newInfo.getPerformingDate().get());
    }
   
	public UUID getParentToDoId()
	{
	    return info.getParentToDoId().orElse(null);
	}

	// turn parent todo id to to-do link (ToDoLinkCreationService)
    public void setParentToDoId(UUID parentToDoId)
    {
    	ensureActorCanChangeThis();
    	
        if (!Objects.isNull(getParentToDoId()))
        {
            throw new DomainException(
                "To-Do \"" + getName() + 
                "\" is already assigned parent To-Do"
            );
        }

        info.setParentToDoId(Optional.ofNullable(parentToDoId));
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

    private boolean isNameCorrect(String name) {

        return StringUtils.hasText(name);
    }

    public String getDescription()
    {
        return info.getDescription().orElse("");
    }

    public void setDescription(String description)
    {
    	ensureActorCanChangeThis();
    	
        info.setDescription(Optional.of(description));
    }

    public ToDoPriority getPriority()
    {
        return info.getPriority();
    }

    public void changePriorityType(ToDoPriorityType priorityType)
    {
    	ensureActorCanChangeThis();
    	
        ToDoPriority newPriority = getPriority().changeType(priorityType);

        setPriority(newPriority);
    }

    public void changePriorityValue(float value)
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

        this.setPriority(priority);
    }

    public LocalDateTime getCreationDate()
    {
        return info.getCreationDate();
    }

    private void setCreationDate(LocalDateTime creationDate)
    {
    	if (!Objects.isNull(getCreationDate()))
    	{
    		throw new DomainException("To-Do \"" + getName() + "\"'s creation date is already performed");
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

        this.setAuthor(author);
    }

    public ToDoNoteInfo getNoteInfo(UUID noteId)
    {
        ToDoNote note = notes.getById(noteId);

        return note.getInfo();
    }

    public void addNote(ToDoNote note)
    {
    	ensureActorCanAddNote(note);
    	
        notes.add(note.clone());    
    }

    private void ensureActorCanAddNote(ToDoNote note) {
		
    	ensureActorCanChangeThis();
    	
    	if (actor().getAllowedToDoNoteCreationCount() >= notes.count())
    	{
    		throw new DomainException("The created To-Do note count limit is reached");
    	}
	}

	public void changeNote(ToDoNoteInfo noteInfo)
    {
    	ensureActorCanChangeThis();
    	
        ToDoNote note = notes.getById(noteInfo.getId());

        note.setInfo(noteInfo);
    }

    public void removeNote(UUID noteId)
    {
    	ensureActorCanChangeThis();
    	
        notes.removeById(noteId);   
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

    private void ensureActorCanPerformThis() {
		
    	workingRules().getPerformingRule().ensureToDoCanPerformedByUser(this, actor());
	}
    
	public LocalDateTime getPerformingDate()
    {
        return info.getPerformingDate().orElse(null);
    }
	
	public void performByOverlapping(ToDo overlappingToDo)
	{
		ensureThisPerformingCanBeOverlappedBy(overlappingToDo);
		
		setPerformingDate(overlappingToDo.getPerformingDate());
	}
	
    private void ensureThisPerformingCanBeOverlappedBy(ToDo overlappingToDo) {
		
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
        	throw new DomainException("To-Do's performing isn't assigned");
        }
        
        if (performingDate.isBefore(info.getCreationDate()))
        {
        	throw new DomainException("To-Do \"" + getName() + "\"'s performing date can't be earlier than creation date");
        }

        info.setPerformingDate(Optional.of(performingDate));
    }
    
    public boolean isPerformed()
    {
    	return !Objects.isNull(getPerformingDate());
    }
    
    @Override
	public ToDo clone() {
		
		var clonedToDo = super.clone();
		
		clonedToDo.notes = notes.clone();
						
		return clonedToDo;
	}
}
