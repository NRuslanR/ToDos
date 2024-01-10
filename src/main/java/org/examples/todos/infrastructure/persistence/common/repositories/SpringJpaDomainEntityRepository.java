package org.examples.todos.infrastructure.persistence.common.repositories;

import java.util.Collection;
import java.util.Optional;

import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.entities.formers.DomainEntityFormer;
import org.examples.todos.infrastructure.persistence.common.converters.DomainEntityInfoConverter;
import org.examples.todos.infrastructure.persistence.common.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class SpringJpaDomainEntityRepository<

	Entity extends DomainEntity, 
	EntityInfo extends DomainEntityInfo,
	Identity,
	JpaEntity extends BaseEntity
> 

extends JpaDomainEntityRepository<Entity, EntityInfo, Identity, JpaEntity>
{
	private final JpaRepository<JpaEntity, Identity> jpaRepository;
	
	protected SpringJpaDomainEntityRepository(
		JpaRepository<JpaEntity, Identity> jpaRepository,
		DomainEntityInfoConverter<EntityInfo, JpaEntity> entityInfoConverter,
		DomainEntityFormer<Entity, EntityInfo> entityFormer
	) 
	{
		super(entityInfoConverter, entityFormer);
		
		this.jpaRepository = jpaRepository;
	}

	@Override
	protected Collection<JpaEntity> doFindAll() 
	{
		return jpaRepository.findAll();
	}

	@Override
	protected Optional<JpaEntity> doFindById(Identity id) 
	{
		return jpaRepository.findById(id);
	}

	@Override
	protected void doSave(JpaEntity jpaEntity) 
	{
		jpaRepository.save(jpaEntity);
	}

	@Override
	protected void doSaveAll(Iterable<JpaEntity> jpaEntities) 
	{
		jpaRepository.saveAll(jpaEntities);
	}

	@Override
	protected void doRemove(JpaEntity jpaEntity) 
	{
		jpaRepository.delete(jpaEntity);
	}

	@Override
	protected void doRemoveAll(Iterable<JpaEntity> jpaEntities) 
	{
		jpaRepository.deleteAll(jpaEntities);
	}
}
