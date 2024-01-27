package org.examples.todos.infrastructure.persistence.common.converters;

import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

public class ModelMapperDomainEntityInfoConverter<
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
		var sourceType = new TypeToken<Source>() {}.getType();
		
		return mapper.map(entityInfo, sourceType);
	}

	@Override
	public EntityInfo convert(Source other) 
	{
		var entityInfoType = new TypeToken<EntityInfo>() {}.getType();
		
		return mapper.map(other, entityInfoType);
	}

}
