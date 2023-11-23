package org.examples.todos.domain.todos.performing.rules;

import org.examples.todos.domain.common.entities.rules.DomainEntityRelationshipRuleException;
import org.examples.todos.domain.todos.actors.ToDo;

public class StandardToDoOverlappingPerformingRule implements ToDoOverlappingPerformingRule {

	@Override
	public void ensureSatisfiedFor(ToDo targetToDo, ToDo overlappingToDo) throws DomainEntityRelationshipRuleException {
	
		ensureOverlappingToDoIsParentForTargetToDo(overlappingToDo, targetToDo);
		ensureTargetToDoIsNotPerformed(targetToDo);
		
	}

	private void ensureOverlappingToDoIsParentForTargetToDo(ToDo overlappingToDo, ToDo targetToDo) {
		
		if (overlappingToDo.getId() != targetToDo.getParentToDoId())
		{
			throw new ToDoOverlappingPerformingRuleException(
				"To-Do \"" + overlappingToDo.getName() + "\" isn't parent for To-Do \"" +
				targetToDo.getName() + "\" for overlapping performing"
			);
		}
		
	}

	private void ensureTargetToDoIsNotPerformed(ToDo targetToDo) {
		
		if (targetToDo.isPerformed())
		{
			throw new ToDoOverlappingPerformingRuleException(
				"To-Do \"" + targetToDo.getName() + "\" is already performed"
			);
		}
		
	}

}
