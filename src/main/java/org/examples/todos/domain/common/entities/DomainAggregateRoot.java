package org.examples.todos.domain.common.entities;

import java.util.Objects;

import org.examples.todos.domain.common.base.DomainObject;
import org.examples.todos.domain.common.entities.rules.DomainAggregateWorkingRules;
import org.examples.todos.domain.common.errors.DomainException;

@SuppressWarnings("unchecked")
public abstract class DomainAggregateRoot<
	Id,
	Info extends DomainEntityInfo<Id, Info>,
	WorkingRules extends DomainAggregateWorkingRules,
	Actor extends DomainEntity,
	AggregateRoot extends DomainAggregateRoot<Id, Info, WorkingRules, Actor, AggregateRoot>

> extends DomainEntity<Id, Info, AggregateRoot>
{
	private WorkingRules workingRules;
	private Actor actor;
	private boolean isInEditing;
	
	
	protected DomainAggregateRoot(Info fullInfo, WorkingRules workingRules, Actor actor) {
		super(fullInfo);
		
		setWorkingRules(workingRules);
		setActorByWorkingRules(actor, workingRules);
	}

	private void setWorkingRules(WorkingRules workingRules) {
		
		if (Objects.isNull(workingRules))
		{
			throw new DomainException("Working rules must be assigned to object before it may use");
		}
		
		this.workingRules = workingRules;
	}

	private void setActorByWorkingRules(Actor actor, WorkingRules workingRules) {
		
		if (Objects.isNull(actor))
		{
			throw new DomainException("Actor must be assigned to object before it may use");
		}
		
		workingRules.getViewingRule().ensureCanViewedByActor(this, actor);
		
		this.actor = actor;
	}

	@Override
	protected void setInfo(Info newInfo) {
		
		ensureActorCanChangeThis();
		
		super.setInfo(newInfo);
	}
	
	public void ensureActorCanChangeThis()
    {
    	if (Objects.isNull(actor))
    		return;
    	
    	if (isInEditing)
    		return;
    	
    	workingRules.getChangingRule().ensureCanChangedByActor(this, actor);
    	
    	isInEditing = true;
    }
	
	public void ensureActorCanRemoveThis()
	{
		workingRules.getRemovingRule().ensureCanRemovedByActor(this, actor);
	}
	
	protected WorkingRules workingRules()
	{
		return workingRules;
	}
	
	protected Actor actor()
	{
		return actor;
	}
}
