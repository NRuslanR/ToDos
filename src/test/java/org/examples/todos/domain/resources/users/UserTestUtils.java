package org.examples.todos.domain.resources.users;

import java.util.UUID;

import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;

public class UserTestUtils 
{
	public static User createSimpleUserWithResetClaims()
	{
		return createSimpleUserWithClaims(0, 0, false, false);
	}
	
	public static User createSimpleUserWithClaims(
		int allowedToDoCreationCount, 
		int allowedToDoNoteCreationCount, 
		boolean canEditForeignToDos, 
		boolean canRemoveForeignToDos
	)
	{
		return new User(
			createSimpleUserInfoWithClaims(
				allowedToDoCreationCount,
				allowedToDoNoteCreationCount, 
				canEditForeignToDos, 
				canRemoveForeignToDos
			)
		);
	}
	
	public static UserInfo createSimpleUserInfoWithResetClaims() 
	{
		return createSimpleUserInfoWithClaims(0, 0, false, false);
	}
	
	public static UserInfo createSimpleUserInfoWithClaims(
		int allowedToDoCreationCount, 
		int allowedToDoNoteCreationCount, 
		boolean canEditForeignToDos, 
		boolean canRemoveForeignToDos
	)
	{
		return new UserInfo(
			UUID.randomUUID(), 
			new UserName("a", "b", "c"), 
			createSimpleRole(
				allowedToDoCreationCount, 
				allowedToDoNoteCreationCount,
				canEditForeignToDos,
				canRemoveForeignToDos
			)
		);
	}
	
	private static UserRole createSimpleRoleWithResetClaims()
	{
		return createSimpleRole(0, 0, false, false);
	}

	private static UserRole createSimpleRole(
		int allowedToDoCreationCount, 
		int allowedToDoNoteCreationCount, 
		boolean canEditForeignToDos, 
		boolean canRemoveForeignToDos
	) 
	{
		return new UserRole(
			new UserRoleInfo(
				UUID.randomUUID(), 
				"a", 
				new UserRoleClaims(
					allowedToDoCreationCount, 
					allowedToDoNoteCreationCount, 
					canEditForeignToDos, 
					canRemoveForeignToDos
				)
			)
		);
	}
}
