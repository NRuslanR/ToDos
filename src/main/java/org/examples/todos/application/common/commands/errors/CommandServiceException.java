package org.examples.todos.application.common.commands.errors;

import org.examples.todos.application.common.errors.ServiceException;

public class CommandServiceException extends ServiceException {
    
    public CommandServiceException()
    {

    }

    public CommandServiceException(String message)
    {
        super(message);
    }

    public CommandServiceException(Throwable cause)
    {
        super(cause);
    }

    public CommandServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
