package org.examples.todos.domain.common.entities;

public abstract class CloneableEntityInfo<E extends CloneableEntityInfo<E>> implements Cloneable 
{
    @SuppressWarnings("unchecked")
    public E clone()
    {
        try
        {
            return (E)super.clone();
        }

        catch (CloneNotSupportedException exception)
        {
            return null;
        }
    }
}
