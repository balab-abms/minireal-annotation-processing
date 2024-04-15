package org.simreal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code SimChart} annotation is used to mark methods that generate or manipulate charts in a simulation model.
 * The {@code name} attribute of the annotation represents the name of the chart.
 * <p>
 * This annotation is retained in the source code and is not included in the compiled class files.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface SimChart {
    /**
     * Returns the name of the chart.
     *
     * @return A string representing the name of the chart.
     */
    String name();
}
