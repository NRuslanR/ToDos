package org.examples.todos.domain.operations.todos.performing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.actors.ToDo;
import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoList;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoTestUtils;
import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.decisionsupport.search.doubles.StubToDoFinder;
import org.examples.todos.domain.operations.todos.forming.StandardToDoFormer;
import org.examples.todos.domain.operations.todos.forming.ToDoFormer;
import org.examples.todos.domain.resources.users.UserTestUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StandardToDoOverlappingPerformingServiceTests 
{
	private ToDoOverlappingPerformingService toDoOverlappingPerformingService;
	private ToDoList toDoList;
	private ToDo toDoForPerforming;
	
	private static ToDoFormer toDoFormer;
	
	@BeforeAll
	public static void setupTestSuite()
	{
		toDoFormer = new StandardToDoFormer();
	}
	
	@BeforeEach
	public void setupTest()
	{
		var actor = UserTestUtils.createSimpleUserWithResetClaims();
		var toDoInfo = ToDoTestUtils.createSimpleToDoInfo("To-Do", ToDoPriority.Low(0), actor);
		
		toDoForPerforming = toDoFormer.formToDo(toDoInfo, actor);
		
		toDoList = createTestToDoListForPerformingOverlapping(toDoForPerforming);
		
		var toDoFinder = new StubToDoFinder(toDoList);
		
		toDoOverlappingPerformingService = new StandardToDoOverlappingPerformingService(toDoFinder);
	}
	
	@Test
	public void should_Return_ValidChildPerformedToDoList_When_RootToDoForPerformingIsValid()
	{
		var alreadyPerformedToDoInfos = getAlreadyPerformedToDoInfosFrom(toDoList);
		
		var performedToDoList = toDoOverlappingPerformingService.performToDo(toDoForPerforming);
		
		assertEquals(toDoList.count() + 1, performedToDoList.count());
		
		var performedToDos = IterableUtils.toList(performedToDoList);
		
		assertTrue(
			CollectionUtils.containsAll(
				performedToDos, 
				CollectionUtils.union(toDoList, Arrays.asList(toDoForPerforming))
			)
		);
		
		assertTrue(performedToDos.stream().allMatch(ToDo::isPerformed));
		
		for (var alreadyPerformedToDoInfo : alreadyPerformedToDoInfos)
		{
			assertEquals(
				alreadyPerformedToDoInfo.getValue1(), 
				performedToDoList.getById(alreadyPerformedToDoInfo.getValue0()).getPerformingDate()
			);
		}
	}
	
	private List<Pair<UUID, LocalDateTime>> getAlreadyPerformedToDoInfosFrom(ToDoList target) {
		
		return 
			IterableUtils
				.toList(target)
				.stream()
				.filter(ToDo::isPerformed)
				.map(item -> Pair.with(item.getId(), item.getPerformingDate()))
				.toList();
	}

	private static ToDoList createTestToDoListForPerformingOverlapping(ToDo root)
	{
		var toDoList = new ToDoList();
		
		var toDo = createTestSimpleToDo(root.getName() + "#1", root.getId());
		
		toDoList.add(toDo);
		
		toDo = createTestSimpleToDo(root.getName() + "#2", root.getId());
		
		toDoList.add(toDo);
		
		var performedChild = 
			createTestSimplePerformedToDo(
				toDo.getName() + ".1", 
				toDo.getId(),
				LocalDateTime.of(LocalDate.of(2076, Month.APRIL, 1), LocalTime.now())
			);
		
		toDoList.add(performedChild);
		
		var notPerformedChild = createTestSimpleToDo(toDo.getName() + ".2", toDo.getId());
		
		toDoList.add(notPerformedChild);
		
		toDo = createTestSimpleToDo(performedChild.getName() + ".1", performedChild.getId());
		
		toDoList.add(toDo);
		
		toDo = 
			createTestSimplePerformedToDo(
				notPerformedChild.getName() + ".1", 
				notPerformedChild.getId(), 
				LocalDateTime.of(LocalDate.of(2043,  Month.DECEMBER, 12), LocalTime.now())
			);
		
		toDoList.add(toDo);
		
		return toDoList;
	}
	
	private static ToDo createTestSimplePerformedToDo(
		String name, 
		UUID parentToDoId,
		LocalDateTime performingDateTime)
	{
		var toDoInfo = createTestSimpleToDoInfo(name, parentToDoId);
		
		toDoInfo.setPerformingDate(Intention.of(performingDateTime));
		
		return toDoFormer.formToDo(toDoInfo);
	}
	
	private static ToDo createTestSimpleToDo(String name, UUID parentToDoId)
	{
		var toDoInfo = createTestSimpleToDoInfo(name, parentToDoId);
		
		return toDoFormer.formToDo(toDoInfo);
	}
	
	private static ToDoInfo createTestSimpleToDoInfo(String name, UUID parentToDoId)
	{
		var toDoInfo = 
			ToDoTestUtils
				.createSimpleToDoInfo(
					name, 
					ToDoPriority.Low(0), 
					UserTestUtils.createSimpleUserWithResetClaims()
				);
		
		toDoInfo.setParentToDoId(Intention.of(parentToDoId));
			
		return toDoInfo;
	}
}
