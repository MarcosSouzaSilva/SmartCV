package com.smartcv.smartcv.dto;

import com.smartcv.smartcv.dto.enums.EuaStates;
import com.smartcv.smartcv.dto.enums.Seniority;
import com.smartcv.smartcv.model.PersonalInfo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PersonalInfoDTO {

    private String full_Name; // Nome completo

    private Integer age; // Idade (em anos)

    private String address; // Endereço

    @Enumerated(EnumType.STRING)
    private EuaStates eua_states;

    @Enumerated(EnumType.STRING)
    private Seniority seniority;

    private String city; // Cidade

    private String phone1; // Telefone 1

    private String phone2; // Telefone 2

    private String email; // E-Mail


    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EuaStates getEua_states() {
        return eua_states;
    }

    public void setEua_states(EuaStates eua_states) {
        this.eua_states = eua_states;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonalInfo request (){

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setFull_Name(this.full_Name);
        personalInfo.setAge(this.age);
        personalInfo.setAddress(this.address);
        personalInfo.setEua_states(this.eua_states);
        personalInfo.setCity(this.city);
        personalInfo.setPhone1(this.phone1);
        personalInfo.setPhone2(this.phone2);
        personalInfo.setEmail(this.email);


        return personalInfo;
    }

    @Override
    public String toString() {
        return "PersonalInfoDTO{" +
                "fullName='" + full_Name + '\'' +
                ", gender='" + seniority + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", state='" + eua_states + '\'' +
                ", city='" + city + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}