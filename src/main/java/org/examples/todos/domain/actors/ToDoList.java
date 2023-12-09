package org.examples.todos.domain.actors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.valueobjects.DomainValueObject;

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
	
	public void add(ToDo toDo)
	{
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
		return toDoList.stream().filter(t -> t.getName().equals(toDoName)).findFirst();
	}
	
	public void remove(ToDo toDo)
	{
		toDoList.remove(toDo);
	}
	
	public ToDoList getAllSubToDosFor(ToDo toDo)
	{
		var subToDos =
			toDoList
				.stream()
				.filter(item -> item.getParentToDoId().equals(toDo.getId()))
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
