package org.examples.todos.domain.common.entities;

public class CloneableEntity<T extends CloneableEntity<T>> implements Cloneable {

	@SuppressWarnings("unchecked")
	public T clone()
	{
		try {
			
			return (T)super.clone();
			
		} catch (CloneNotSupportedException exception) {
			
			return null;
		}
	}
}
