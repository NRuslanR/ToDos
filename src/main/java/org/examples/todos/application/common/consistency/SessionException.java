package org.examples.todos.application.common.consistency;

public class SessionException extends RuntimeException {

	public SessionException(String message)
	{
		super(message);
	}
	
	public SessionException(Throwable nested)
	{
		super(nested);
	}
	
	public SessionException(String message, Throwable nested)
	{
		super(message, nested);
	}
}
