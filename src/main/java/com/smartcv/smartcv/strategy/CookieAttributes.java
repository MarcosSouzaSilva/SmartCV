package com.smartcv.smartcv.strategy;

import com.smartcv.smartcv.strategy.impl.CookiesAttributesImpl;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieAttributes implements CookiesAttributesImpl {



    @Override
    public void setCookieAttributes(Cookie cookie) {
        cookie.setMaxAge((int) Duration.ofMinutes(30).getSeconds());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
    }

}