package org.examples.todos.infrastructure.persistence.repositories.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.IterableUtils;
import org.examples.todos.domain.common.entities.DomainEntity;
import org.examples.todos.infrastructure.persistence.common.PersistenceTests;
import org.examples.todos.infrastructure.persistence.common.repositories.DomainEntityRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public abstract class DomainEntityRepositoryTests extends PersistenceTests
{
	private DomainEntityRepository<DomainEntity, UUID> domainEntityRepository;
	private static DomainEntityStoreInitializer<DomainEntity> domainEntityStoreInitializer;
	
	private List<DomainEntity> storeEntities;
	
	public DomainEntityRepositoryTests(
		DomainEntityRepository domainEntityRepository,
		DomainEntityStoreInitializer domainEntityStoreInitializer
	)
	{
		this.domainEntityRepository = domainEntityRepository;
		this.domainEntityStoreInitializer = domainEntityStoreInitializer;
		
		storeEntities = createSeedEntities();
		
		domainEntityStoreInitializer.seed(storeEntities);
	}
	
	protected abstract List<DomainEntity> createSeedEntities();
	
	@AfterAll
	public static void setupForAllTests()
	{
		domainEntityStoreInitializer.clear();
	}

	@Test
	public void should_Returns_AllDomainEntities_When_TheyExists()
	{
		var actualEntities = domainEntityRepository.findAll();
		
		assertDomainEntityListEquals(storeEntities, actualEntities);
	}
	
	protected void assertDomainEntityListEquals(
		Iterable<DomainEntity> expectedEntities,
		Iterable<DomainEntity> actualEntities
	)
	{
		var actualEntityList = IterableUtils.toList(actualEntities);
		
		for (var expected : expectedEntities)
		{
			var actualIndex = actualEntityList.indexOf(expected);
			
			assertTrue(actualIndex != -1);
			
			var actual = actualEntityList.get(actualIndex);
			
			assertDomainEntitiesEquals(expected, actual);
		}
	}

	@Test
	public void should_Returns_ValidDomainEntity_ById_When_ItExists()
	{
		var expected = storeEntities.get(0);
		
		var actual = domainEntityRepository.findById((UUID)expected.getId());
		
		assertTrue(actual.isPresent());
		
		assertDomainEntitiesEquals(expected, actual.get());
	}

	@Test
	public void should_Returns_Null_When_DomainEntity_NotFound_ById()
	{
		var fakeEntityId = UUID.randomUUID();
		
		var actual = domainEntityRepository.findById(fakeEntityId);
		
		assertTrue(actual.isEmpty());
	}
	
	@Test
	public void should_Add_DomainEntity_When_ItValid()
	{
		var expected = createDomainEntityToAdd();
		
		domainEntityRepository.save(expected);
		
		var actual = domainEntityRepository.findById((UUID)expected.getId());
		
		assertDomainEntitiesEquals(expected, actual.get());
	}
	
	protected abstract DomainEntity createDomainEntityToAdd();

	@Test
	public void should_Update_DomainEntity_When_ItExists()
	{
		var expected = storeEntities.get(0);
		
		changeDomainEntityToUpdate(expected);
		
		domainEntityRepository.save(expected);
		
		var actual = domainEntityRepository.findById((UUID)expected.getId());
		
		assertDomainEntitiesEquals(expected, actual.get());
	}
	
	protected abstract void changeDomainEntityToUpdate(DomainEntity entity);

	protected abstract void assertDomainEntitiesEquals(
		DomainEntity expected, 
		DomainEntity actual
	);
	
	@Test
	public void should_Remove_DomainEntity_When_ItExists()
	{
		var entityId = (UUID)storeEntities.get(0).getId();
		
		var actual = domainEntityRepository.findById(entityId);
		
		domainEntityRepository.remove(actual.get());
		
		actual = domainEntityRepository.findById(entityId);
		
		assertTrue(actual.isEmpty());
	}
}
