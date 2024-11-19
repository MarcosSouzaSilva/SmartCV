package com.smartcv.smartcv.dto.inutilizaveis;

public class QualificationsAndActivitiesDTO {

    private String information;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "QualificationsAndActivitiesDTO{" +
                "information='" + information + '\'' +
                '}';
    }

}