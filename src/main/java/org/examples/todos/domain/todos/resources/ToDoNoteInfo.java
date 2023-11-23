package org.examples.todos.domain.todos.resources;

import java.util.UUID;

import org.examples.todos.domain.common.entities.DomainEntityInfo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@NoArgsConstructor
public class ToDoNoteInfo extends DomainEntityInfo<UUID, ToDoNoteBaseInfo, ToDoNoteInfo> {
    
    @Delegate
    public ToDoNoteBaseInfo getBaseInfo()
    {
        return super.getBaseInfo();
    }
}
