package com.smartcv.smartcv.dto.inutilizaveis;

public class WorkExperienceDTO {

    private String company; // Empresa

    private int entryYear; // Ano de Entrada

    private int exitYear; // Ano de Saída

    private String position; // Cargo

    private String mainActivities; // Principais atividades desempenhadas no cargo


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(int entryYear) {
        this.entryYear = entryYear;
    }

    public int getExitYear() {
        return exitYear;
    }

    public void setExitYear(int exitYear) {
        this.exitYear = exitYear;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMainActivities() {
        return mainActivities;
    }

    public void setMainActivities(String mainActivities) {
        this.mainActivities = mainActivities;
    }

    @Override
    public String toString() {
        return "WorkExperienceDTO{" +
                "company='" + company + '\'' +
                ", entryYear=" + entryYear +
                ", exitYear=" + exitYear +
                ", position='" + position + '\'' +
                ", mainActivities='" + mainActivities + '\'' +
                '}';
    }

}