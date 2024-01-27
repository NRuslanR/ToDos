package org.examples.todos.infrastructure.persistence.roles.entities;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity<UUID>  
{    
    private String name;
    
    private String description;
    
    @Accessors(fluent = true)
    @Column(name = "allowed_todo_creation_count")
    private int allowedToDoCreationCount;
    
    @Accessors(fluent = true)
    @Column(name = "allowed_todo_note_creation_count")
    private int allowedToDoNoteCreationCount;
    
    @Accessors(fluent = true)
    @Column(name = "can_edit_foreign_todos")
    private boolean canEditForeignToDos;
    
    @Accessors(fluent = true)
    @Column(name = "can_remove_foreign_todos")
    private boolean canRemoveForeignToDos;
}
