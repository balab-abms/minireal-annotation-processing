package org.simreal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code SimField} annotation is used to mark fields that are associated with a database table.
 * <p>
 * This annotation is retained in the source code and is not included in the compiled class files.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface SimField {
}
