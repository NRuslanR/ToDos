package org.examples.todos.domain.actors;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.valueobjects.DomainValueObject;

public class ToDoPriority extends DomainValueObject<ToDoPriority> {
    
    private ToDoPriorityType type;
    private float value;

    public static ToDoPriority Urgent(float value)
    {
        return new ToDoPriority(ToDoPriorityType.Urgent, value);
    }

    public static ToDoPriority Medium(float value)
    {
        return new ToDoPriority(ToDoPriorityType.Medium, value);
    }

    public static ToDoPriority Low(float value)
    {
        return new ToDoPriority(ToDoPriorityType.Low, value);
    }

    private ToDoPriority()
    {
        
    }

    public ToDoPriority(ToDoPriorityType type, float value)
    {
        setType(type);  
        setValue(value);    
    }

    public ToDoPriorityType type()
    {
        return type;
    }

    public ToDoPriority changeType(ToDoPriorityType newType)
    {
        return new ToDoPriority(newType, value);
    }

    private void setType(ToDoPriorityType type)
    {
        this.type = type;
    }

    public float value()
    {
        return value;
    }

    public ToDoPriority changeValue(float newValue)
    {
        return new ToDoPriority(type, newValue);
    }

    private void setValue(float value)
    {
        if (value < 0)
        {
            throw new DomainException("To-Do priority value can't be negative");
        }

        this.value = value;
    }
}
