package org.examples.todos.application.common.consistency;

public abstract class BaseSession implements Session {

	private boolean started;

	@Override
	public boolean isStarted() {
		
		return started;
	}

	@Override
	public void start() throws SessionException {
		
		doStart();
		
		setStartedFlag(true);
	}

	protected abstract void doStart();

	@Override
	public void commit() throws SessionException {
	
		if (!isStarted())
			throw new SessionException("Session hasn't started !");
		
		doCommit();
		
		setStartedFlag(false);
	}

	protected abstract void doCommit();

	@Override
	public void rollback() throws SessionException {
		
		if (!isStarted())
			throw new SessionException("Session hasn't started !");
		
		doRollback();
		
		setStartedFlag(false);
	}
	
	protected abstract void doRollback();

	private void setStartedFlag(boolean flag) {
		
		started = flag;
	}
}
