package org.examples.todos.infrastructure.persistence.roles.entities;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity<UUID>  
{    
    private String name;
    
    private String description;
}
