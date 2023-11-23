package org.examples.todos.infrastructure.persistence.todos.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "todo_notes")
public class ToDoNoteEntity extends BaseEntity<UUID> 
{
    private String note;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdAt;
}
