package com.smartcv.smartcv.strategy;

import com.smartcv.smartcv.strategy.impl.VerificationImpl;
import org.springframework.stereotype.Component;

@Component
public class IsInvalidPassword implements VerificationImpl {


    @Override
    public boolean verificationOfPassword(String password) {
        return !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*]).{2,20}$");
    }
}