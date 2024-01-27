package org.examples.todos.infrastructure.persistence.config;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.examples.todos.domain.actors.ToDoInfo;
import org.examples.todos.domain.actors.ToDoNote;
import org.examples.todos.domain.actors.ToDoNoteInfo;
import org.examples.todos.domain.actors.ToDoNoteList;
import org.examples.todos.domain.actors.ToDoPriority;
import org.examples.todos.domain.actors.ToDoPriorityType;
import org.examples.todos.domain.common.base.Intention;
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
		
		typeMap.<UserRoleClaims>addMapping(
			UserRoleInfo::getClaims, 
			(d, v) -> {
				
				d.allowedToDoCreationCount(v.allowedToDoCreationCount());
				d.allowedToDoNoteCreationCount(v.allowedToDoNoteCreationCount());
				d.canEditForeignToDos(v.canEditForeignToDos());
				d.canRemoveForeignToDos(v.canRemoveForeignToDos());
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
		
		typeMap.<UserName>addMapping(UserInfo::getName, (d, v) -> {
			
			d.setName(v.firstName());
			d.setSurname(v.middleName());
			d.setPatronymic(v.lastName());
			
		});
		
		typeMap.<UserInfo>addMapping(s -> s, (d, v) -> {
			
				if (Objects.isNull(v.getAddress()))
					return;
				
				var address = v.getAddress().getValue();
				
				d.setAddress(
					new UserAddressEntity(
						v.getId(), 
						address.street(), 
						address.house(),
						address.room()
					)
				);
			});
		
		var inverseTypeMap = mapper.createTypeMap(UserEntity.class, UserInfo.class);
		
		inverseTypeMap.<UserEntity>addMapping(s -> s, (d, v) -> {
			
			d.setName(new UserName(v.getName(), v.getSurname(), v.getPatronymic()));
			
			if (Objects.isNull(v.getAddress()))
				return;
			
			var address = v.getAddress();
			
			d.setAddress(
				Intention.of(
					new UserAddress(
						address.getStreet(), 
						address.getHome(), 
						address.getRoom()
					)
				)
			);
		});
		
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
		
		typeMap.addMapping(s -> s.getPriority().type().name(), ToDoEntity::setPriorityType);
		typeMap.addMapping(s -> s.getPriority().value(), ToDoEntity::setPriorityValue);
		typeMap.addMapping(s -> s.getParentToDoId().getValue(), ToDoEntity::setParentToDoId);
		typeMap.addMapping(s -> s.getDescription().getValue(), ToDoEntity::setDescription);
		typeMap.addMapping(s -> s.getPerformingDate().getValue(), ToDoEntity::setPerformingDate);
		typeMap.addMapping(s -> s.getNotes().getValue(), ToDoEntity::setNotes);
		
		var inverseTypeMap = mapper.createTypeMap(ToDoEntity.class, ToDoInfo.class);
		
		inverseTypeMap.<ToDoEntity>addMapping(
				s -> s, 
				(d, v) ->
					d.setPriority(
						new ToDoPriority(
							ToDoPriorityType.valueOf(v.getPriorityType()), 
							v.getPriorityValue())
					) 
		);
		
		inverseTypeMap.<String>addMapping(ToDoEntity::getDescription, (d, v) -> d.setDescription(Intention.of(v)));
		inverseTypeMap.<UUID>addMapping(ToDoEntity::getParentToDoId, (d, v) -> d.setParentToDoId(Intention.of(v)));
		inverseTypeMap.<LocalDateTime>addMapping(ToDoEntity::getPerformingDate, (d, v) -> d.setPerformingDate(Intention.of(v)));

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
			m -> 
				m.using(converter)
				 .map(ToDoEntity::getNotes, ToDoInfo::setNotes)
		);				
	}
}
