package com.smartcv.smartcv.strategy;

import com.smartcv.smartcv.strategy.impl.CookiesAttributes;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieAttributes implements CookiesAttributes {


    @Override
    public void setCookieAttributes(Cookie cookie) {
        cookie.setMaxAge((int) Duration.ofMinutes(30).getSeconds());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
    }

}