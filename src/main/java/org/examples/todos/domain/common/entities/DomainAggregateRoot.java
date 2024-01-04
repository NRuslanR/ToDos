package org.examples.todos.domain.common.entities;

import java.util.Objects;
import java.util.Optional;

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
	private Optional<WorkingRules> workingRules;
	private Optional<Actor> actor;
	private boolean isInEditing;
	
	protected DomainAggregateRoot(Info fullInfo)
	{
		super(fullInfo);
		
		workingRules = Optional.empty();
		actor = Optional.empty();
	}
	
	public Optional<WorkingRules> getWorkingRules()
	{
		return workingRules;
	}
	
	protected WorkingRules workingRules()
	{
		return workingRules.get();
	}

	public void setWorkingRules(WorkingRules workingRules) {
		
		if (Objects.isNull(workingRules))
		{
			throw new DomainException("Attemp to assign the invalid working rules");
		}
		
		this.workingRules = Optional.of(workingRules);
	}

	public Optional<Actor> getActor()
	{
		return actor;
	}
	
	protected Actor actor()
	{
		return actor.get();
	}
	
	public void setActor(Actor actor) {
		
		ensureWorkingRulesAssigned();
		
		if (Objects.isNull(actor))
		{
			throw new DomainException("Attempt to assign the invalid actor");
		}
		
		workingRules().getViewingRule().ensureCanViewedByActor(this, actor);
		
		this.actor = Optional.of(actor);
		
		isInEditing = false;
	}
	
	@Override
	protected void setInfo(Info newInfo) {
		
		ensureActorCanChangeThis();
		
		super.setInfo(newInfo);
	}
	
	public void ensureActorCanChangeThis()
    {
    	if (isInEditing)
    		return;
    	
    	if (Objects.isNull(actor))
    		return;
    	
    	if (actor.isEmpty())
    	{
    		throw new DomainAggregateRootActorNotAssignedException(
    			"Actor must be assigned before this can be used"
    		);
    	}
    	
    	workingRules().getChangingRule().ensureCanChangedByActor(this, actor());
    	
    	isInEditing = true;
    }
	
	public void ensureActorCanRemoveThis()
	{
		ensureWorkingRulesAssigned();
		
		workingRules().getRemovingRule().ensureCanRemovedByActor(this, actor());
	}
	
	private void ensureWorkingRulesAssigned()
	{
		if (Objects.isNull(workingRules))
			return;
		
		if (workingRules.isEmpty())
		{
			throw new DomainAggregateWorkingRulesNotAssignedException("Working rules must be assigned before");
		}
	}
}
