package org.simreal.processor.DTO;

/**
 * The {@code ChartDTO} class is a data transfer object that encapsulates chart-related information.
 * It is primarily used to gather and organize chart information derived from chart annotations.
 * <p>
 * Each instance of {@code ChartDTO} represents a unique chart, identified by its name and associated method.
 * The chart name and method name are both represented as strings.
 * <p>
 * This class provides getter and setter methods for the chart name and method name.
 */
public class ChartDTO {
    /**
     * The name of the chart. This is a unique identifier for the chart.
     */
    public String chartName;

    /**
     * The name of the method associated with the chart. This represents the method that generates or manipulates the chart.
     */
    public String methodName;

    /**
     * Returns the name of the chart.
            *
            * @return A string representing the name of the chart.
            */
    public String getChartName() {
        return chartName;
    }

    /**
     * Sets the name of the chart.
     *
     * @param chartName A string representing the name of the chart.
     */
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    /**
     * Returns the name of the method associated with the chart.
     *
     * @return A string representing the name of the method.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the name of the method associated with the chart.
     *
     * @param methodName A string representing the name of the method.
     */

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
