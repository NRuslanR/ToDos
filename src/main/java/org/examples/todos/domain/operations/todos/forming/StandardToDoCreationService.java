package org.examples.todos.domain.operations.todos.forming;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.decisionsupport.search.ToDoFinder;
import org.examples.todos.domain.resources.users.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StandardToDoCreationService implements ToDoCreationService {

	private final ToDoFinder toDoFinder;
	private final ToDoFormer toDoFormer;
	
	@Override
	public ToDo createToDoFor(ToDoInfo toDoInfo, User actor) throws DomainException {
		
		ensureToDoCanCreatedForUser(toDoInfo, actor);
		
		var sanitizedToDoInfo = sanitizeToDoInfo(toDoInfo);

		var toDo = toDoFormer.formToDo(sanitizedToDoInfo, actor);
		
		if (!Objects.isNull(toDoInfo.getNotes()))
			toDo.addNotes(toDoInfo.getNotes().getValue());
		
		return toDo;
	}

	private void ensureToDoCanCreatedForUser(ToDoInfo toDoInfo, User actor) {
		
		var userToDoList = toDoFinder.findUserTodos(actor);
		
		ensureCreatedToDoCountLimitNotReached(userToDoList, actor);
	}

	private void ensureCreatedToDoCountLimitNotReached(ToDoList userToDoList, User actor) {
		
		if (actor.getAllowedToDoCreationCount() <= userToDoList.count())
		{
			throw new ToDoCreationCountLimitReachedException(
				"The created To-Do count limit is reached"
			);
		}
	}
	
	private ToDoInfo sanitizeToDoInfo(ToDoInfo toDoInfo) {
		
		var sanitizedToDoInfo = toDoInfo.clone();
		
		sanitizedToDoInfo.setId(UUID.randomUUID());
		sanitizedToDoInfo.setCreationDate(LocalDateTime.now());
		sanitizedToDoInfo.setParentToDoId(null);
		sanitizedToDoInfo.setPerformingDate(null);
		sanitizedToDoInfo.setNotes(null);
		
		return sanitizedToDoInfo;
	}
}
