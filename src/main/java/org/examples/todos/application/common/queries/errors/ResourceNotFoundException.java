package org.examples.todos.application.common.queries.errors;

public class ResourceNotFoundException extends QueryServiceException {
    
    public ResourceNotFoundException()
    {

    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause)
    {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
