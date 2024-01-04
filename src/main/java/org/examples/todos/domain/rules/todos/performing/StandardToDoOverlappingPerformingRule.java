package org.examples.todos.domain.rules.todos.performing;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.common.errors.DomainException;

public class StandardToDoOverlappingPerformingRule implements ToDoOverlappingPerformingRule {

	@Override
	public void ensureSatisfiedFor(ToDo targetToDo, ToDo overlappingToDo) throws DomainEntityRelationshipRuleException {
	
		ensureOverlappingToDoIsParentForTargetToDo(overlappingToDo, targetToDo);
		ensureOverlappingToDoIsPerformed(overlappingToDo);
		ensureTargetToDoIsNotPerformed(targetToDo);
		
	}

	private void ensureOverlappingToDoIsParentForTargetToDo(ToDo overlappingToDo, ToDo targetToDo) {
		
		if (overlappingToDo.getId() != targetToDo.getParentToDoId())
		{
			throw new OverlappingToDoIsNotParentException(
				"To-Do \"" + overlappingToDo.getName() + "\" isn't parent for To-Do \"" +
				targetToDo.getName() + "\" for overlapping performing"
			);
		}
		
	}
	
	private void ensureOverlappingToDoIsPerformed(ToDo overlappingToDo) {
		
		if (!overlappingToDo.isPerformed())
		{
			throw new OverlappingToDoIsNotPerformedException(
				"Overlapping To-Do \"" 
				+ overlappingToDo.getName() + 
				"\" must be performed before the sub To-Dos' overlappig performing"
			);
		}
	}

	private void ensureTargetToDoIsNotPerformed(ToDo targetToDo) {
		
		if (targetToDo.isPerformed())
		{
			throw new ToDoIsAlreadyPerformedException(
				"To-Do \"" + targetToDo.getName() + "\" is already performed"
			);
		}
		
	}

}
