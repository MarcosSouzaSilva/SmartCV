package com.smartcv.smartcv.dto.inutilizaveis;

public class ExtraInformationDTO {

    private String information;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "ExtraInformationDTO{" +
                "information='" + information + '\'' +
                '}';
    }
}
