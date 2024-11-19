package com.smartcv.smartcv.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Education {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String company; // Empresa

    private int entryYear; // Ano de Entrada

    private int exitYear; // Ano de Saída

    private String position; // Cargo

    private String mainActivities; // Principais atividades desempenhadas no cargo

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    public Education(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public Education() {

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
