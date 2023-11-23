package org.examples.todos.web.api.errors.handling;

import org.examples.todos.application.common.errors.NoResourceAccessRightsException;
import org.examples.todos.application.common.queries.errors.ResourceNotFoundException;
import org.examples.todos.web.api.errors.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiError> handleResourceNotFoundException(
        ResourceNotFoundException exception
    )
    {
        return new ResponseEntity<ApiError>(
            new ApiError(exception.getMessage()), 
            HttpStatus.NOT_FOUND
        );
    }
    
    @ExceptionHandler(NoResourceAccessRightsException.class)
    protected ResponseEntity<ApiError> handleNoResourceAccessRightsException(
        NoResourceAccessRightsException exception
    )
    {
        return new ResponseEntity<ApiError>(
            new ApiError(exception.getMessage()), 
            HttpStatus.FORBIDDEN
        );
    }

}
