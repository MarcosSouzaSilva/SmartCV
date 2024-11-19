package com.smartcv.smartcv.dto;

public class EducationDTO {

    private String course; // Curso

    private String institution; // Instituição

    private int graduationYear; // Ano de Conclusão


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Override
    public String toString() {
        return "EducationDTO{" +
                "course='" + course + '\'' +
                ", institution='" + institution + '\'' +
                ", graduationYear=" + graduationYear +
                '}';
    }
}