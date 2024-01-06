package org.examples.todos.domain.actors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.valueobjects.DomainValueObject;
import org.examples.todos.domain.resources.users.User;

/**
 * Refactor: create base class for domain entity list 
 * and make ToDoList and ToDoNoteList inheritable from it 
 */
@SuppressWarnings("unused")
public class ToDoList extends DomainValueObject<ToDoList> implements Iterable<ToDo>  
{
	private List<ToDo> toDoList;
	
	public static ToDoList of(Iterable<ToDo> toDos)
	{
		return new ToDoList(toDos);
	}
	
	public static ToDoList of(ToDo toDo) 
	{	
		return new ToDoList(toDo);
	}

	public ToDoList(Iterable<ToDo> toDos)
	{
		this();
		
		addAll(toDos);
	}
	
	public ToDoList(ToDo toDo)
	{
		this();
		
		add(toDo);
	}
	
	public ToDoList()
	{
		toDoList = new ArrayList<>();
	}
	
	@Override
	public Iterator<ToDo> iterator() 
	{		
		return toDoList.iterator();	
	}
	
	public void addAll(Iterable<ToDo> toDos)
	{
		toDos.forEach(this::add);
	}
	
	public ToDo getById(UUID id)
	{
		var toDo = findById(id);
		
		return toDo.orElseThrow(() -> 
			new DomainException("To-Do wasn't found by id in the list")
		);
	}
	
	public ToDo getByName(String name)
	{
		var toDo = findByName(name);
		
		return toDo.orElseThrow(() ->
			new DomainException("To-Do with name \"" + name + "\" wasn't found in the list")
		);
	}
	private Optional<ToDo> findById(UUID id) 
	{
		return toDoList.stream().filter(item -> item.getId().equals(id)).findFirst();
	}

	public void add(ToDo toDo)
	{
		Objects.requireNonNull(toDo);
		
		if (contains(toDo))
		{
			throw new DomainException("To-Do \"" + toDo.getName() + "\" is already in list");
		}
		
		toDoList.add(toDo);
	}
	
	public boolean contains(ToDo toDo)
	{
		return toDoList.contains(toDo);
	}
	
	public boolean containsByName(String toDoName)
	{
		return findByName(toDoName).isPresent();
	}
	
	public Optional<ToDo> findByName(String toDoName)
	{
		return toDoList.stream().filter(t -> Objects.equals(t.getName(), toDoName)).findFirst();
	}
	
	public void remove(ToDo toDo)
	{
		toDoList.remove(toDo);
	}
	
	public ToDoList getAllSubToDosFor(ToDo toDo)
	{
		return getToDosByCondition(item -> Objects.equals(item.getParentToDoId(), toDo.getId()));
	}
	
	public ToDoList getAllUserToDos(User user)
	{
		return getToDosByCondition(item -> Objects.equals(item.getAuthor(), user));
	}
	
	private ToDoList getToDosByCondition(Predicate<ToDo> condition)
	{
		var subToDos =
			toDoList
				.stream()
				.filter(condition)
				.toList();
		
		return ToDoList.of(subToDos);
	}
	
	public int count()
	{
		return toDoList.size();
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof ToDoList))
			return false;
		
		var otherToDoList = (ToDoList)other;
		
		return toDoList.equals(otherToDoList.toDoList);
	}

	@Override
	public ToDoList clone() 
	{
		return new ToDoList(toDoList.stream().map(ToDo::clone).toList());
	}
}
