package org.examples.todos.domain.common.objectvalues;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DomainValueObject<T extends DomainValueObject<T>> {
    
    public boolean equals(T other)
    {
        return super.equals(other);
    }

    @Override
    public boolean equals(Object other)
    {
        return
            (other != null) && 
            (other.getClass().equals(getClass())) &&
            attributesMatched(other);
    }

    private boolean attributesMatched(Object other) {
        
        return attributeStream().allMatch(attributeMatchedPredicateFor(other));
    }

    private Predicate<Method> attributeMatchedPredicateFor(Object other)
    {
        return m -> {

            try
            {
                return m.invoke(this).equals(m.invoke(other));
            }

            catch (Exception exception)
            {
                return false;
            }
        };
    }

    @Override
    public int hashCode()
    {
        var attributesValues = attributesValuesForHashCode();

        return Objects.hash(attributesValues);
    }

    private Object[] attributesValuesForHashCode() {

        return attributesValueStream().map(v -> !Objects.isNull(v) ? v : 0).toArray();

    }

    private Stream<Object> attributesValueStream() {
        
        return attributeStream().map(this::getSafelyAttributeValue);
    }

    private Object getSafelyAttributeValue(Method attribute)
    {
        try
        {
            return attribute.invoke(this);
        }

        catch (Exception exception)
        {
            return null;
        }
    }

    private Stream<Method> attributeStream()
    {
        return
            Arrays
                .stream(getClass().getDeclaredMethods())
                .filter(
                    m -> 
                        !m.getReturnType().equals(Void.class) && 
                        m.getParameterTypes().length == 0 
                );
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
