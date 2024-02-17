package org.examples.todos.infrastructure.persistence.users.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;
import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity<UUID>  
{    
    private String name;

    private String surname;

    private String patronymic;

    @OneToOne
    @MapsId
    private UserAddressEntity address;
    
    @OneToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = {
            @JoinColumn(name = "user_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "role_id")
        }
    )
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();
}
