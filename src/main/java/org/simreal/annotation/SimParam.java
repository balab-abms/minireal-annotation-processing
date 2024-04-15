package org.simreal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code SimParam} annotation is used to mark parameters that are associated with a simulation model.
 * The {@code value} attribute of the annotation represents the value of the parameter.
 * <p>
 * This annotation is retained in the source code and is not included in the compiled class files.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
public @interface SimParam {
    /**
     * Returns the value of the parameter.
     *
     * @return A string representing the value of the parameter.
     */
    String value();
}
