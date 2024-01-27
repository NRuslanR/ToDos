package org.examples.todos.infrastructure.persistence.users.entities;

import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_addresses")
@NoArgsConstructor
public class UserAddressEntity extends BaseEntity<UUID> 
{
	private String street;
	
	private String home;
	
	private String room;
	
	public UserAddressEntity(UUID id, String street, String home, String room)
	{
		super(id);
		
		setStreet(street);
		setHome(home);
		setRoom(room);
	}
}
