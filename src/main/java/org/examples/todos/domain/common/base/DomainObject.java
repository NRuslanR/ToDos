package org.examples.todos.domain.common.base;

public abstract class DomainObject<E extends DomainObject<E>> implements Cloneable 
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
