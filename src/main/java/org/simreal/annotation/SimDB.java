package org.simreal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code SimDB} annotation is used to mark methods that are associated with a database table.
 * The {@code name} attribute of the annotation represents the name of the database table.
 * <p>
 * This annotation is retained in the source code and is not included in the compiled class files.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface SimDB {
    /**
     * Returns the name of the database table.
     *
     * @return A string representing the name of the database table.
     */
    String name();
}
