package org.examples.todos.infrastructure.persistence.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoNote;
import org.examples.todos.domain.actors.ToDoNoteInfo;
import org.examples.todos.domain.actors.ToDoNoteList;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoPriorityType;
import org.examples.todos.domain.common.base.Intention;
import org.examples.todos.domain.resources.roles.UserRole;
import org.examples.todos.domain.resources.roles.UserRoleClaims;
import org.examples.todos.domain.resources.roles.UserRoleInfo;
import org.examples.todos.domain.resources.users.UserAddress;
import org.examples.todos.domain.resources.users.UserInfo;
import org.examples.todos.domain.resources.users.UserName;
import org.examples.todos.infrastructure.persistence.roles.entities.RoleEntity;
import org.examples.todos.infrastructure.persistence.todos.entities.ToDoEntity;
import org.examples.todos.infrastructure.persistence.todos.entities.ToDoNoteEntity;
import org.examples.todos.infrastructure.persistence.users.entities.UserAddressEntity;
import org.examples.todos.infrastructure.persistence.users.entities.UserEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperDomainEntityInfoMappingConfig 
{	
	public static void configure(ModelMapper mapper)
	{
		customizeMapper(mapper);
	}

	private static void customizeMapper(ModelMapper mapper) 
	{
		mapper
			.getConfiguration()
			.setMatchingStrategy(MatchingStrategies.STRICT)
			.setSkipNullEnabled(true);
		
		customizeMappings(mapper);
		
	}

	private static void customizeMappings(ModelMapper mapper) 
	{
		customizeUserRoleInfoMappings(mapper);
		customizeUserMappings(mapper);
		customizeToDoNoteMappings(mapper);
		customizeToDoMappings(mapper);
	}

	private static void customizeUserRoleInfoMappings(ModelMapper mapper) 
	{
		var typeMap = mapper.createTypeMap(UserRoleInfo.class, RoleEntity.class);
		
		typeMap.<String>addMapping(
			s -> s.getDescription().getValue(), 
			RoleEntity::setDescription
		);
		
		Converter<UserRoleClaims, Integer> allowedToDoCreationCount = 
			ctx -> ctx.getSource().allowedToDoCreationCount();
		
		Converter<UserRoleClaims, Integer> allowedToDoNoteCreationCount =
			ctx -> ctx.getSource().allowedToDoNoteCreationCount();
			
		Converter<UserRoleClaims, Boolean> canEditForeignToDos =
			ctx -> ctx.getSource().canEditForeignToDos();
			
		Converter<UserRoleClaims, Boolean> canRemoveForeignToDos = 
			ctx -> ctx.getSource().canRemoveForeignToDos();
			
		typeMap.addMappings(
			m -> { 
				m
					.using(allowedToDoCreationCount)
					.map(UserRoleInfo::getClaims, RoleEntity::setAllowedToDoCreationCount);
				m
					.using(allowedToDoNoteCreationCount)
					.map(UserRoleInfo::getClaims, RoleEntity::setAllowedToDoNoteCreationCount);
				m
					.using(canEditForeignToDos)
					.map(UserRoleInfo::getClaims, RoleEntity::setCanEditForeignToDos);
				m
					.using(canRemoveForeignToDos)
					.map(UserRoleInfo::getClaims, RoleEntity::setCanRemoveForeignToDos);
			}
		);
		
		var inverseTypeMap = mapper.createTypeMap(RoleEntity.class, UserRoleInfo.class);
		
		inverseTypeMap.<String>addMapping(
			RoleEntity::getDescription, 
			(d, v)-> d.setDescription(Intention.of(v))
		);
		
		inverseTypeMap.addMappings(m -> m.skip(UserRoleInfo::setClaims));
		
		Converter<RoleEntity, UserRoleInfo> converter = ctx -> {
			
			var source = ctx.getSource();
			
			ctx
				.getDestination()
				.setClaims(new UserRoleClaims(
					source.allowedToDoCreationCount(),
					source.allowedToDoNoteCreationCount(),
					source.canEditForeignToDos(),
					source.canEditForeignToDos()
				));
			
			return ctx.getDestination();
		};
		
		inverseTypeMap.setPostConverter(converter);
	}

	private static void customizeUserMappings(ModelMapper mapper) 
	{
		var typeMap = mapper.createTypeMap(UserInfo.class, UserEntity.class);
		
		Converter<UserName, String> firstName = ctx -> ctx.getSource().firstName();
		Converter<UserName, String> middleName = ctx -> ctx.getSource().middleName();
		Converter<UserName, String> lastName = ctx -> ctx.getSource().lastName();
		
		typeMap.addMappings(
			m -> {
				m.using(firstName).map(UserInfo::getName, UserEntity::setName);
				m.using(middleName).map(UserInfo::getName, UserEntity::setPatronymic);
				m.using(lastName).map(UserInfo::getName, UserEntity::setSurname);
			}
		);
		
		typeMap.addMappings(m -> m.skip(UserEntity::setAddress));
		
		Converter<UserRole, List<RoleEntity>> roles = 
			ctx -> {
				
				var roleEntity = mapper.map(ctx.getSource().getInfo(), RoleEntity.class);
				
				var roleEntities = new ArrayList<RoleEntity>();
				
				roleEntities.add(roleEntity);
				
				return roleEntities;
			};
			
		typeMap.addMappings(m -> m.using(roles).map(UserInfo::getRole, UserEntity::setRoles));
		
		Converter<UserInfo, UserEntity> converter =
			ctx -> {
				
				var address = ctx.getSource().getAddress().getValue();				
				
				if (!Objects.isNull(address))
				{
					ctx
						.getDestination()
						.setAddress(
							new UserAddressEntity(
								ctx.getSource().getId(), 
								address.street(), 
								address.house(), 
								address.room()
							)
						);
				}
				
				return ctx.getDestination();
			};
		
		typeMap.setPostConverter(converter);
		
		var inverseTypeMap = mapper.createTypeMap(UserEntity.class, UserInfo.class);
		
		Converter<List<RoleEntity>, UserRole> inverseRole = 
			ctx -> {
				
				var roleEntity = ctx.getSource().get(0);
				
				return new UserRole(mapper.map(roleEntity, UserRoleInfo.class));
			};
				
		inverseTypeMap.addMappings(
			m -> m.using(inverseRole).map(UserEntity::getRoles, UserInfo::setRole)
		);
		
		Converter<UserAddressEntity, Intention<UserAddress>> inverseAddress =
			ctx -> {
				
				var addressEntity = ctx.getSource();
				
				return Intention.of(
					new UserAddress(
						addressEntity.getStreet(), 
						addressEntity.getHome(), 
						addressEntity.getRoom()
					)
				); 
			};
			
		inverseTypeMap.addMappings(
			m -> m.using(inverseAddress).map(UserEntity::getAddress, UserInfo::setAddress)
		);
		
		inverseTypeMap.addMappings(m -> m.skip(UserInfo::setName));
		
		Converter<UserEntity, UserInfo> userPostConverter =
			ctx -> {
				
				var userEntity = ctx.getSource();
				var user = ctx.getDestination();
				
				user.setName(
					UserName.of(
						userEntity.getName(), 
						userEntity.getPatronymic(), 
						userEntity.getSurname()
					)
				);
				
				return user;			
				
			};
			
		inverseTypeMap.setPostConverter(userPostConverter);		
	}

	private static void customizeToDoNoteMappings(ModelMapper mapper) 
	{
		var typeMap = mapper.createTypeMap(ToDoNoteInfo.class, ToDoNoteEntity.class);
		
		typeMap.addMapping(ToDoNoteInfo::getText, ToDoNoteEntity::setNote);

		var inverseTypeMap = mapper.createTypeMap(ToDoNoteEntity.class, ToDoNoteInfo.class);
		
		inverseTypeMap.addMapping(ToDoNoteEntity::getNote, ToDoNoteInfo::setText);
	}

	private static void customizeToDoMappings(ModelMapper mapper) 
	{
		var typeMap = mapper.createTypeMap(ToDoInfo.class, ToDoEntity.class);
		
		typeMap.addMapping(s -> s.getParentToDoId().getValue(), ToDoEntity::setParentToDoId);
		typeMap.addMapping(s -> s.getDescription().getValue(), ToDoEntity::setDescription);
		typeMap.addMapping(s -> s.getPerformingDate().getValue(), ToDoEntity::setPerformingDate);
		typeMap.addMapping(s -> s.getNotes().getValue(), ToDoEntity::setNotes);
		typeMap.addMapping(s -> s.getPriority().value(), ToDoEntity::setPriorityValue);
		typeMap.addMapping(s -> s.getPriority().typeName(), ToDoEntity::setPriorityType);
		
		Converter<ToDoNoteList, List<ToDoNoteEntity>> noteEntities =
			ctx -> {
				
				var notes = ctx.getSource();
				
				return 
					Objects.isNull(notes) ? 
					null :
					notes
						.stream()
						.map(n -> mapper.map(n.getInfo(), ToDoNoteEntity.class))
						.toList();
			};
			
		typeMap.addMappings(
			m -> m.using(noteEntities).map(s -> s.getNotes().getValue(), ToDoEntity::setNotes)
		);
		
		var inverseTypeMap = mapper.createTypeMap(ToDoEntity.class, ToDoInfo.class);
		
		Converter<ToDoEntity, ToDoInfo> toDoInfoPostConverter =
			ctx -> {
				
				ctx
					.getDestination()
					.setPriority(
						new ToDoPriority(
							ToDoPriorityType.valueOf(ctx.getSource().getPriorityType()), 
							ctx.getSource().getPriorityValue()
						)
					);
				
				return ctx.getDestination();
			};
			
		inverseTypeMap.setPostConverter(toDoInfoPostConverter);
		
		Converter<String, Intention<String>> description =
			ctx -> Intention.of(ctx.getSource());
			
		Converter<UUID, Intention<UUID>> parentToDoId = 
			ctx -> Intention.of(ctx.getSource());
			
		Converter<LocalDateTime, Intention<LocalDateTime>> performingDateTime = 
			ctx -> Intention.of(ctx.getSource());
			
		Converter<Collection<ToDoNoteEntity>, Intention<ToDoNoteList>> converter = 
				ctx -> 
					Intention.of(
						new ToDoNoteList(
							ctx.getSource()
								.stream()
								.map(i -> new ToDoNote(mapper.map(i, ToDoNoteInfo.class)))
								.toList()			
						)
					);
					
		inverseTypeMap.addMappings(
			m -> {
				
				m.using(description).map(ToDoEntity::getDescription, ToDoInfo::setDescription);
				m.using(parentToDoId).map(ToDoEntity::getParentToDoId, ToDoInfo::setParentToDoId);
				m.using(performingDateTime).map(ToDoEntity::getPerformingDate, ToDoInfo::setPerformingDate);		
				m.using(converter).map(ToDoEntity::getNotes, ToDoInfo::setNotes);
			}
		);		
	}
}
