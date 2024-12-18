package com.smartcv.smartcv.dto;

import com.smartcv.smartcv.model.Education;

public class EducationDTO {

    private String course; // Curso

    private String institution; // Instituição

    private int graduation_year; // Ano de Conclusão do curso


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

    public int getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(int graduation_year) {
        this.graduation_year = graduation_year;
    }

    @Override
    public String toString() {
        return "EducationDTO{" +
                "course='" + course + '\'' +
                ", institution='" + institution + '\'' +
                ", graduationYear=" + graduation_year +
                '}';
    }

    public Education request (){

        Education education = new Education();

        education.setCourse(this.course);
        education.setGraduation_year(this.graduation_year);
        education.setInstitution(this.institution);


        return education;
    }
}