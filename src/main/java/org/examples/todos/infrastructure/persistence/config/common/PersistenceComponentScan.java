package org.examples.todos.infrastructure.persistence.config.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ComponentScan(basePackages = { "org.examples.todos.infrastructure.persistence.*" })
public @interface PersistenceComponentScan 
{

}
