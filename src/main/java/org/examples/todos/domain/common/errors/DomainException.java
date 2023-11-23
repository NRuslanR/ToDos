package org.examples.todos.domain.common.errors;

public class DomainException extends RuntimeException {
    
    public DomainException(String message)
    {
        super(message);
    }

    public DomainException(Throwable t)
    {
        super(t);
    }

    public DomainException(String message, Throwable t)
    {
        super(message, t);
    }
}
