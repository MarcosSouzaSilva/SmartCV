package com.smartcv.smartcv.dto;

import com.smartcv.smartcv.model.Courses;

public class CoursesDTO {

    private String course; // Curso

    private String institution; // Instituição

    private String moth; // Instituição

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

    public String getMoth() {
        return moth;
    }

    public void setMoth(String moth) {
        this.moth = moth;
    }

    @Override
    public String toString() {
        return "CoursesDTO{" +
                "course='" + course + '\'' +
                ", institution='" + institution + '\'' +
                ", moth='" + moth + '\'' +
                ", graduation_year=" + graduation_year +
                '}';
    }

    public Courses request (){

        Courses courses = new Courses();

        courses.setCourse(this.course);
        courses.setGraduation_year(this.graduation_year);
        courses.setInstitution(this.institution);
        courses.setMoth(this.moth);


        return courses;
    }
}