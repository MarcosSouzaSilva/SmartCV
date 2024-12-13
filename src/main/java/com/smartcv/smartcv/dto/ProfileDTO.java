package com.smartcv.smartcv.dto;

import com.smartcv.smartcv.dto.enums.Profession;
import com.smartcv.smartcv.model.Users;

public class ProfileDTO {

    private String username;

    private String email;

    private Profession profession;

    public ProfileDTO() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Users request (){
        Users users = new Users();
        users.setUsername(this.username);
        users.setProfession(this.profession);
        users.setEmail(this.email);
        return users;
    }

    public void fromDtoCadastro(Users user) {
        this.username = user.getUsername();
        this.profession = user.getProfession();
        this.email = user.getEmail();
    }
}
