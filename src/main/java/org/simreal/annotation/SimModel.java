package org.simreal.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code SimModel} annotation is used to mark types (classes or interfaces) that are associated with a model.
 * <p>
 * This annotation is retained in the source code and is not included in the compiled class files.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface SimModel {
}
