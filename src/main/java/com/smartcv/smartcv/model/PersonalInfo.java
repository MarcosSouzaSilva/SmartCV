package com.smartcv.smartcv.model;

import com.smartcv.smartcv.dto.enums.EuaStates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class PersonalInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @NotNull(message = "The full name cannot be null. Please provide your name.")
    private String full_Name; // Nome completo

    @NotNull(message = "Age cannot be null. Please enter your age.")
    private Integer age; // Idade

    @NotNull(message = "The address cannot be null. Please provide your address.")
    private String address; // Endereço

    @NotNull(message = "The state cannot be null. Please select a valid state.")
    @Enumerated(EnumType.STRING)
    private EuaStates eua_states; // Estados (USA)

    @NotNull(message = "The city cannot be null. Please provide your city.")
    private String city; // Cidade

    @NotNull(message = "The primary phone number cannot be null. Please provide a valid phone number.")
    private String phone1; // Telefone 1

    @NotNull(message = "The secondary phone number cannot be null. Please provide a valid phone number.")
    private String phone2; // Telefone 2

    @NotNull(message = "The email cannot be null. Please provide a valid email address.")
    private String email; // E-Mail

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "Usuário esta null nesse caralho")
    private Users user;

    public PersonalInfo(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public PersonalInfo() {
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

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String fullName) {
        this.full_Name = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public void setEua_states(EuaStates state) {
        this.eua_states = state;
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

    @Override
    public String toString() {
        return "PersonalInfoDTO{" +
                "fullName='" + full_Name + '\'' +
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