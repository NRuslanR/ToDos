package org.examples.todos.domain.common.base;

import java.util.Objects;

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
	
	public T getValueOrDefault(T defaultValue)
	{
		return !Objects.isNull(value) ? value : defaultValue;
	}
}
