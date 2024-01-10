package org.examples.todos.domain.common.entities.formers;

import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.domain.common.entities.DomainEntityInfo;
import org.examples.todos.domain.common.errors.DomainException;

public interface DomainEntityFormer<
	Entity extends DomainEntity,
	EntityInfo extends DomainEntityInfo
>
{
	Entity formEntity(EntityInfo info) throws DomainException;
}
