package org.examples.todos.infrastructure.persistence.common.converters;

import org.examples.todos.domain.common.entities.DomainEntityInfo;

public interface DomainEntityInfoConverter<EntityInfo extends DomainEntityInfo, Source> 
{
	Source convert(EntityInfo entityInfo);
	EntityInfo convert(Source other);
}
