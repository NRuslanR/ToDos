package org.examples.todos.infrastructure.persistence.common.converters;

import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.modelmapper.ModelMapper;

public abstract class ModelMapperDomainEntityInfoConverter<
	EntityInfo extends DomainEntityInfo,
	Source
>
	implements DomainEntityInfoConverter<EntityInfo, Source>
{
	private ModelMapper mapper;
	
	public ModelMapperDomainEntityInfoConverter(ModelMapper mapper)
	{
		this.mapper = mapper; 
	}
	
	@Override
	public Source convert(DomainEntityInfo entityInfo) 
	{
		var sourceType = getSourceType();

		return mapper.map(entityInfo, sourceType);
	}

	protected abstract Class<Source> getSourceType();

	@Override
	public EntityInfo convert(Source other) 
	{
		var entityInfoType = getEntityInfoType();
		
		return mapper.map(other, entityInfoType);
	}

	protected abstract Class<EntityInfo> getEntityInfoType();

}
