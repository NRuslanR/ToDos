package org.examples.todos.domain.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intention<T> {

	public static <T> Intention<T> of(T value)
	{
		return new Intention<T>(value);
	}
	
	private T value;
}
