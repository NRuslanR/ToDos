package org.examples.todos.infrastructure.persistence.todos.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "todos")
public class ToDoEntity extends BaseEntity<UUID>
{
    private UUID parentToDoId;

	private int priority;

	private String name; 

	private String description;
	
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private LocalDateTime creationDate;

    @Column(name = "performed_at")
    @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime performingDate;

	@OneToOne
	@JoinColumn(name = "author_id")
	private UserEntity author;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "note_id")
	private List<ToDoNoteEntity> notes;  
}
