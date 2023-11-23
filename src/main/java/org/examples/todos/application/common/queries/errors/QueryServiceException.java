package org.examples.todos.application.common.queries.errors;

import org.examples.todos.application.common.errors.ServiceException;

public class QueryServiceException extends ServiceException {
    
    public QueryServiceException()
    {

    }
    
    public QueryServiceException(String message)
    {
        super(message);
    }

    public QueryServiceException(Throwable cause)
    {
        super(cause);
    }

    public QueryServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
