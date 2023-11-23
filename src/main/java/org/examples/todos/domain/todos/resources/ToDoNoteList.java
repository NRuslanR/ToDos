package org.examples.todos.domain.todos.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.objectvalues.DomainValueObject;

public class ToDoNoteList 
    extends DomainValueObject<ToDoNoteList> 
    implements Iterable<ToDoNote> 
{
    private List<ToDoNote> toDoNoteList;

    public ToDoNoteList()
    {
        toDoNoteList = new ArrayList<>();
    }

    @Override
    public Iterator<ToDoNote> iterator() {
        
        return toDoNoteList.iterator();
    }
    
    public int count()
    {
        return toDoNoteList.size();
    }

    public ToDoNote get(int index)
    {
        return toDoNoteList.get(index);
    }

    public ToDoNote getById(UUID id)
    {
        var note = findNoteByCondition(i -> i.getId().equals(id));

        return note.orElseThrow(
            () -> new DomainException(
                "To-Do note not found"
            )
        );
    }

    public ToDoNote getByName(String name)
    {
        var note = findNoteByCondition(i -> i.getName().equals(name));

        return note.orElseThrow(
            () -> new DomainException(
                "To-Do note with name \"" + name + "\" not found"
            )
        );
    }

    private Optional<ToDoNote> findNoteByCondition(Predicate<ToDoNote> condition)
    {
        return toDoNoteList.stream().filter(condition).findFirst();
    }

    public void add(ToDoNote toDoNote)
    {
        if (contains(toDoNote))
        {
            throw new DomainException("Attempt to attach the same note for to-do");
        }

        toDoNoteList.add(toDoNote);
    }

    public void remove(ToDoNote toDoNote)
    {
        toDoNoteList.remove(toDoNote);
    }

    public boolean contains(ToDoNote toDoNote)
    {
        return toDoNoteList.contains(toDoNote);
    }

    public void clear()
    {
        toDoNoteList.clear();
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof ToDoNoteList))
            return false;
        
        var otherToDoNoteList = (ToDoNoteList)other;

        return toDoNoteList.equals(otherToDoNoteList.toDoNoteList);
    }

    public void removeByName(String noteName) {

        removeByCondition(note -> note.getName().equals(noteName));
    }

    public void removeById(UUID id)
    {
        removeByCondition(note -> note.getId().equals(id));
    }

    private void removeByCondition(Predicate<ToDoNote> condition)
    {
        toDoNoteList.removeIf(condition);
    }
}
