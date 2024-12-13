package com.smartcv.smartcv.strategy;

import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.strategy.impl.ValidationImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailValid implements ValidationImpl {


    public boolean emailValid(String users) {
        return users.contains("@gmail.com")
                || users.contains("@hotmail.com")
                || users.contains("@protonmail.com")
                || users.contains("@zoho.com")
                || users.contains("@icloud.com")
                || users.contains("@yahoo.com")
                || users.contains("@outlook.com");
    }
}