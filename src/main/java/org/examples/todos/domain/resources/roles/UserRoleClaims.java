package org.examples.todos.domain.resources.roles;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.valueobjects.DomainValueObject;

public class UserRoleClaims extends DomainValueObject<UserRoleClaims> 
{
    private int allowedToDoCreationCount;
    private int allowedToDoNoteCreationCount;
    private boolean canEditForeignToDos;
    private boolean canRemoveForeignToDos;

    public UserRoleClaims(
        int allowedToDoCreationCount,
        int allowedToDoNoteCreationCount,
        boolean canEditForeignToDos,
        boolean canRemoveForeignToDos
    )
    {
        setAllowedToDoCreationCount(allowedToDoCreationCount);
        setAllowedToDoNoteCreationCount(allowedToDoNoteCreationCount);
        setCanEditForeignToDos(canEditForeignToDos);
        setCanRemoveForeignToDos(canRemoveForeignToDos);
    }

    public int allowedToDoCreationCount()
    {
        return allowedToDoCreationCount;
    }

    private void setAllowedToDoCreationCount(int value)
    {
        if (value < 0)
        {
            throw new DomainException(
                "Allowed To-Do creation count can't be negative"
            );
        }

        allowedToDoCreationCount = value;
    }

    public int allowedToDoNoteCreationCount()
    {
        return allowedToDoNoteCreationCount;
    }

    private void setAllowedToDoNoteCreationCount(int value)
    {
        if (value < 0)
        {
            throw new DomainException(
                "Allowed To-Do note creation count can't be negative"
            );
        }

        allowedToDoNoteCreationCount = value;
    }

    public boolean canEditForeignToDos()
    {
        return canEditForeignToDos;
    }

    private void setCanEditForeignToDos(boolean value)
    {
        canEditForeignToDos = value;
    }

    public boolean canRemoveForeignToDos()
    {
        return canRemoveForeignToDos;
    }

    private void setCanRemoveForeignToDos(boolean value)
    {
        canRemoveForeignToDos = value;
    }
}
