package org.examples.todos.domain.actors;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.shared.utils.StringUtils;

public class ToDoNote extends DomainEntity<UUID, ToDoNoteInfo, ToDoNote> 
{
    public ToDoNote(ToDoNoteInfo info)
    {
    	super(info);
    }

	@Override
    public void setInfo(ToDoNoteInfo newInfo)
    {
        super.setInfo(newInfo);

        setName(newInfo.getName());
        setText(newInfo.getText());
        setCreatedAt(newInfo.getCreatedAt());
    }

    public String getName()
    {
        return info.getName();
    }

    public void setName(String name)
    {
        if (!isNameCorrect(name))
        {
            throw new DomainException("To-do name must contain text");
        }

        info.setName(name);
    }
    
    private boolean isNameCorrect(String name) {

        return StringUtils.hasText(name);
    }

    public String getText()
    {
        return info.getText();
    }

    public void setText(String text)
    {
        if (!isTextCorrect(text))
        {
            throw new DomainException("To-do text must not be empty");
        }

        info.setText(text); 
    }

    private boolean isTextCorrect(String value) {
        
        return StringUtils.hasText(value);
    }

    public LocalDateTime getCreatedAt()
    {
        return info.getCreatedAt();
    }

    private void setCreatedAt(LocalDateTime createdAt)
    {
    	if (Objects.isNull(createdAt))
    	{
    		throw new DomainException("To-Do note's creation date must be assigned");
    	}
    	
        if (!Objects.isNull(info.getCreatedAt()))
        {
            throw new DomainException("To-Do note's creation date can't be changed");
        }

        info.setCreatedAt(createdAt);
    }

    @Override
    public boolean equals(Object other)
    {
        return super.equals(other) && getName().equals(((ToDoNote)other).getName());
    }
}
