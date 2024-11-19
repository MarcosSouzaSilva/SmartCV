package com.smartcv.smartcv.dto;

import com.smartcv.smartcv.dto.enums.EuaStates;
import com.smartcv.smartcv.dto.enums.Gender;
import com.smartcv.smartcv.dto.enums.MaritalStatus;
import com.smartcv.smartcv.model.PersonalInfo;
import com.smartcv.smartcv.model.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PersonalInfoDTO {

    private String fullName; // Nome completo

    private Gender gender; // Sexo

    private int age; // Idade (em anos)

    private MaritalStatus maritalStatus; // Estado Civil

    private boolean hasChildren; // Tem Filho(s)?

    private String address; // Endereço

    private EuaStates state; // Estado

    private String city; // Cidade

    private String phone1; // Telefone 1

    private String phone2; // Telefone 2

    private String email; // E-Mail

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EuaStates getState() {
        return state;
    }

    public void setState(EuaStates state) {
        this.state = state;
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
        personalInfo.setFullName(this.fullName);
        personalInfo.setGender(this.gender);
        personalInfo.setAge(this.age);
        personalInfo.setMaritalStatus(this.maritalStatus);
        personalInfo.setHasChildren(this.hasChildren);
        personalInfo.setAddress(this.address);
        personalInfo.setState(this.state);
        personalInfo.setCity(this.city);
        personalInfo.setPhone1(this.phone1);
        personalInfo.setPhone2(this.phone2);
        personalInfo.setAddress(this.email);
        return personalInfo;
    }

    @Override
    public String toString() {
        return "PersonalInfoDTO{" +
                "fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", hasChildren=" + hasChildren +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}