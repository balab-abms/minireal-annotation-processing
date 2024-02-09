package org.simreal.processor.DTO;

import java.util.ArrayList;

public class MetaModelDTO {
    public ModelDTO modelDTO;
    public ArrayList<ParamDTO> paramDTOList;
    public ArrayList<DatabaseDTO> dbDTOList;
    public ArrayList<ChartDTO> chartDTOList;
    public VisualDTO visualDTO;

    public ModelDTO getModelDTO() {
        return modelDTO;
    }

    public void setModelDTO(ModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    public ArrayList<ParamDTO> getParamDTOList() {
        return paramDTOList;
    }

    public void setParamDTOList(ArrayList<ParamDTO> paramDTOList) {
        this.paramDTOList = paramDTOList;
    }

    public ArrayList<DatabaseDTO> getDbDTOList() {
        return dbDTOList;
    }

    public void setDbDTOList(ArrayList<DatabaseDTO> dbDTOList) {
        this.dbDTOList = dbDTOList;
    }

    public ArrayList<ChartDTO> getChartDTOList() {
        return chartDTOList;
    }

    public void setChartDTOList(ArrayList<ChartDTO> chartDTOList) {
        this.chartDTOList = chartDTOList;
    }

    public VisualDTO getVisualDTO() {
        return visualDTO;
    }

    public void setVisualDTO(VisualDTO visualDTO) {
        this.visualDTO = visualDTO;
    }
}
