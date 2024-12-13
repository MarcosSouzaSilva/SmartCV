package com.smartcv.smartcv.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Courses {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String course; // Curso

    private String institution; // Instituição

    private Integer graduation_year; // Ano de Conclusão

    private String moth; // Ano de Conclusão

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    public Courses(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public Courses() {

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getMoth() {
        return moth;
    }

    public void setMoth(String moth) {
        this.moth = moth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(Integer graduationYear) {
        this.graduation_year = graduationYear;
    }
}
