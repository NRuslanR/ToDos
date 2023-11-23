package org.examples.todos.domain.todos.actors;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainAggregateRoot;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.todos.resources.ToDoNote;
import org.examples.todos.domain.todos.resources.ToDoNoteInfo;
import org.examples.todos.domain.todos.resources.ToDoNoteList;
import org.examples.todos.domain.todos.resources.ToDoPriority;
import org.examples.todos.domain.todos.resources.ToDoPriorityType;
import org.examples.todos.domain.todos.rules.ToDoWorkingRules;
import org.examples.todos.domain.users.actors.User;
import org.examples.todos.shared.utils.StringUtils;

public class ToDo extends DomainAggregateRoot<
	UUID, 
	ToDoBaseInfo, 
	ToDoInfo, 
	ToDoWorkingRules, 
	User, 
	ToDo
> 
{
	private ToDoNoteList notes = new ToDoNoteList();

	ToDo(ToDoBaseInfo baseInfo, ToDoWorkingRules workingRules, User actor) {
		super(baseInfo, workingRules, actor);
	}

	ToDo(ToDoInfo fullInfo, ToDoWorkingRules workingRules, User actor) {
		super(fullInfo, workingRules, actor);
	}

	@Override
    public void setInfo(ToDoInfo newInfo)
    {
    	super.setInfo(newInfo);
    	
    	setParentToDoId(newInfo.getParentToDoId());
    	setAuthor(newInfo.getAuthor());
    	setCreationDate(newInfo.getCreationDate());
    	setDescription(newInfo.getDescription());
    	setName(newInfo.getName());
    	setPerformingDate(newInfo.getPerformingDate());
    	setPriority(newInfo.getPriority());
    }
   
	public UUID getParentToDoId()
	{
	    return info.getParentToDoId();
	}

    public void setParentToDoId(UUID parentToDoId)
    {
    	ensureActorCanChangeThis();
    	
        if (!Objects.isNull(parentToDoId))
        {
            throw new DomainException(
                "To-Do \"" + getName() + 
                "\" is already assigned parent To-Do"
            );
        }

        info.setParentToDoId(parentToDoId);
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
        return info.getDescription();
    }

    public void setDescription(String description)
    {
    	ensureActorCanChangeThis();
    	
        info.setDescription(description);
    }

    public ToDoPriority getPriority()
    {
        return info.getPriority();
    }

    public void changePriorityType(ToDoPriorityType priorityType)
    {
    	ensureActorCanChangeThis();
    	
        ToDoPriority newPriority = info.getPriority().changeType(priorityType);

        setPriority(newPriority);
    }

    public void changePriorityValue(float value)
    {
    	ensureActorCanChangeThis();
    	
        ToDoPriority newPriority = info.getPriority().changeValue(value);

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
    	if (!Objects.isNull(info.getCreationDate()))
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
    	if (!Objects.isNull(info.getAuthor()))
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
    	ensureActorCanChangeThis();
    	
        notes.add(note.clone());    
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
        return info.getPerformingDate();
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

	private void setPerformingDate(LocalDateTime performingDate)
    {
    	LocalDateTime currentPerformingDate = getPerformingDate();
    	
        if (!Objects.isNull(currentPerformingDate))
        {
            throw new DomainException("To-Do \"" + getName() + "\" is already performed");
        }
        
        if (performingDate.isBefore(info.getCreationDate()))
        {
        	throw new DomainException("To-Do \"" + getName() + "\"'s performing date can't be earlier than creation date");
        }

        info.setPerformingDate(performingDate);
    }
    
    public boolean isPerformed()
    {
    	return !Objects.isNull(info.getPerformingDate());
    }
}
