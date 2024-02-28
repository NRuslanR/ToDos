package org.examples.todos.infrastructure.persistence.roles.entities;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class RoleEntity extends BaseEntity<UUID>  
{    
    private String name;
    
    private String description;
    
    @Accessors(fluent = true)
    @Column(name = "allowed_todo_creation_count")
    @Setter(value = AccessLevel.NONE)
    private int allowedToDoCreationCount;
    
    public void setAllowedToDoCreationCount(int value)
    {
    	allowedToDoCreationCount = value;
    }
    
    @Accessors(fluent = true)
    @Column(name = "allowed_todo_note_creation_count")
    @Setter(AccessLevel.NONE)
    private int allowedToDoNoteCreationCount;
    
    public void setAllowedToDoNoteCreationCount(int value)
    {
    	allowedToDoNoteCreationCount = value;
    }
    
    @Accessors(fluent = true)
    @Column(name = "can_edit_foreign_todos")
    @Setter(AccessLevel.NONE)
    private boolean canEditForeignToDos;
    
    public void setCanEditForeignToDos(boolean value)
    {
    	canEditForeignToDos = value;
    }
    
    @Accessors(fluent = true)
    @Column(name = "can_remove_foreign_todos")
    @Setter(value = AccessLevel.NONE)
    private boolean canRemoveForeignToDos;
    
    public void setCanRemoveForeignToDos(boolean value)
    {
    	canRemoveForeignToDos = value;
    }
}
