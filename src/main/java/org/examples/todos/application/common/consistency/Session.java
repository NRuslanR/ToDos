package org.examples.todos.application.common.consistency;

public interface Session {
	
	boolean isStarted();
	
	void start() throws SessionException;
	void commit() throws SessionException;
	void rollback() throws SessionException;
}
