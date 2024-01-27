package org.examples.todos.infrastructure.persistence.config;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;

public class DevJpaVendorConfig extends JpaVendorConfig
{
	protected Collection<String> getPropertyNames() 
	{
		return CollectionUtils.union(
			super.getPropertyNames(),
			Arrays.asList(
				"spring.h2.console.enabled",
				"spring.h2.console.path",
				"spring.h2.console.settings.trace",
				"spring.h2.console.settings.web-allow-others"
			)
		);

	}
	
}
