package org.simreal.processor.DTO;

import java.util.ArrayList;

/**
 * The {@code MetaModelDTO} class is a data transfer object that encapsulates all the information
 * needed for annotation processing. It houses the model details, parameters, database, and chart
 * information.
 * <p>
 * Each instance of {@code MetaModelDTO} represents a unique meta model, identified by its model,
 * parameters, database, and chart details.
 * <p>
 * This class provides getter and setter methods for the model, parameters, database, and chart details.
 */
public class MetaModelDTO {
    /**
     * The details of the model.
     */
    public ModelDTO modelDTO;

    /**
     * The list of parameters associated with the model.
     */
    public ArrayList<ParamDTO> paramDTOList;

    /**
     * The list of database details associated with the model.
     */
    public ArrayList<DatabaseDTO> dbDTOList;

    /**
     * The list of chart details associated with the model.
     */
    public ArrayList<ChartDTO> chartDTOList;

    /**
     * Returns the details of the model.
     *
     * @return The details of the model.
     */
    public ModelDTO getModelDTO() {
        return modelDTO;
    }

    /**
     * Sets the details of the model.
     *
     * @param modelDTO The details of the model.
     */
    public void setModelDTO(ModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    /**
     * Returns the list of parameters associated with the model.
     *
     * @return The list of parameters associated with the model.
     */
    public ArrayList<ParamDTO> getParamDTOList() {
        return paramDTOList;
    }

    /**
     * Sets the list of parameters associated with the model.
     *
     * @param paramDTOList The list of parameters associated with the model.
     */
    public void setParamDTOList(ArrayList<ParamDTO> paramDTOList) {
        this.paramDTOList = paramDTOList;
    }

    /**
     * Returns the list of database details associated with the model.
     *
     * @return The list of database details associated with the model.
     */
    public ArrayList<DatabaseDTO> getDbDTOList() {
        return dbDTOList;
    }

    /**
     * Sets the list of database details associated with the model.
     *
     * @param dbDTOList The list of database details associated with the model.
     */
    public void setDbDTOList(ArrayList<DatabaseDTO> dbDTOList) {
        this.dbDTOList = dbDTOList;
    }

    /**
     * Returns the list of chart details associated with the model.
     *
     * @return The list of chart details associated with the model.
     */
    public ArrayList<ChartDTO> getChartDTOList() {
        return chartDTOList;
    }

    /**
     * Sets the list of chart details associated with the model.
     *
     * @param chartDTOList The list of chart details associated with the model.
     */
    public void setChartDTOList(ArrayList<ChartDTO> chartDTOList) {
        this.chartDTOList = chartDTOList;
    }

}
