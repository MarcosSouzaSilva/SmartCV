package com.smartcv.smartcv.dto;

import com.smartcv.smartcv.model.Users;

public class PerfilDto {

    private String username;

    private String email;



    public PerfilDto() {
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

    public Users request (){
        Users users = new Users();
        users.setUsername(this.username);
        users.setEmail(this.email);
        return users;
    }

    public void fromDtoCadastro(Users user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
