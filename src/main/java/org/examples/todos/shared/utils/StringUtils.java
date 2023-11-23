package org.examples.todos.shared.utils;

import java.util.Objects;

public class StringUtils {
    
    private StringUtils()
    {

    }

    public static boolean hasText(String value)
    {
        return !Objects.isNull(value) && !value.trim().isEmpty();
    }
}
