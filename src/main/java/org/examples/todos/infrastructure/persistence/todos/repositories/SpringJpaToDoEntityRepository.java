package org.examples.todos.infrastructure.persistence.todos.repositories;

import java.util.List;
import java.util.UUID;

import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaToDoEntityRepository extends JpaRepository<ToDoEntity, UUID>
{
	List<ToDoEntity> findByAuthorId(UUID authorId);
	
	@Query(
		value = 
		"with recursive get_all_sub_todos as (" +
		"select * from todos where id = :toDoId " +
		"union " +
		"select t.* from todos t " +
		"join get_all_sub_todos sub on sub.id = t.parent_todo_id " +
		") " +
		"select * from get_all_sub_todos",
		nativeQuery = true
	)
	List<ToDoEntity> findAllSubToDosRecursivelyFor(@Param("toDoId") UUID toDoId);
}
