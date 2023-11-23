package org.examples.todos.application.common.errors;

public class NoResourceAccessRightsException extends ServiceException {
    
    public NoResourceAccessRightsException()
    {

    }

    public NoResourceAccessRightsException(String message)
    {
        super(message);
    }

    public NoResourceAccessRightsException(Throwable cause)
    {
        super(cause);
    }

    public NoResourceAccessRightsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
