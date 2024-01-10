package org.examples.todos.infrastructure.persistence.common.repositories;

import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.formers.DomainEntityFormer;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;

public abstract class JpaDomainEntityRepository<

	Entity extends DomainEntity, 
	EntityInfo extends DomainEntityInfo,
	Identity,
	JpaEntity extends BaseEntity

> implements DomainEntityRepository<Entity, Identity>
{
	protected final DomainEntityInfoConverter<EntityInfo, JpaEntity> entityInfoConverter;
	protected final DomainEntityFormer<Entity, EntityInfo> entityFormer;
	
	protected JpaDomainEntityRepository(
		DomainEntityInfoConverter<EntityInfo, JpaEntity> entityInfoConverter,
		DomainEntityFormer<Entity, EntityInfo> entityFormer
	)
	{
		this.entityInfoConverter = entityInfoConverter;
		this.entityFormer = entityFormer;
	}
	
	@Override
	public Iterable<Entity> findAll() 
	{
		Iterable<JpaEntity> jpaEntities = doFindAll();
		
		return toDomainEntities(jpaEntities);
	}

	protected abstract Iterable<JpaEntity> doFindAll();

	@Override
	public Entity findById(Identity id) 
	{
		JpaEntity jpaEntity = doFindById(id);
		
		var entityInfo = entityInfoConverter.convert(jpaEntity);
		
		return entityFormer.formEntity(entityInfo);
	}

	protected abstract JpaEntity doFindById(Identity id);

	@Override
	public void save(Entity entity) 
	{
		var entityInfo = entity.getInfo();
		
		var jpaEntity = entityInfoConverter.convert((EntityInfo)entityInfo);
		
		doSave(jpaEntity);
	}

	protected abstract void doSave(JpaEntity jpaEntity);

	@Override
	public void saveAll(Iterable<Entity> entities) 
	{
		var jpaEntities = toJpaEntities(entities);
		
		doSaveAll(jpaEntities);
	}
	
	protected abstract void doSaveAll(Iterable<JpaEntity> jpaEntities);

	@Override
	public void remove(Entity entity) 
	{
		var entityInfo = entity.getInfo();
		
		var jpaEntity = entityInfoConverter.convert((EntityInfo)entityInfo);
		
		doRemove(jpaEntity);
	}

	protected abstract void doRemove(Object jpaEntity);

	@Override
	public void removeAll(Iterable<Entity> entities) 
	{
		var jpaEntities = toJpaEntities(entities);
		
		doRemoveAll(jpaEntities);
	}
	
	protected abstract void doRemoveAll(Iterable<JpaEntity> jpaEntities);

	protected Iterable<Entity> toDomainEntities(Iterable<JpaEntity> jpaEntities)
	{
		return 
			IterableUtils
				.toList(jpaEntities)
				.stream()
				.map(entityInfoConverter::convert)
				.map(entityFormer::formEntity)
				.toList();
	}
	
	protected Iterable<JpaEntity> toJpaEntities(Iterable<Entity> domainEntities)
	{
		return
			IterableUtils.
				toList(domainEntities)
				.stream()
				.map(entity -> (EntityInfo)entity.getInfo())
				.map(entityInfoConverter::convert)
				.toList();
	}
}
