package com.smartcv.smartcv.service.userProfile;

import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.UsersRepository;
import com.smartcv.smartcv.strategy.CookieAttributes;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ServiceUserProfile {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private CookieAttributes cookieAttributes;


    public ModelAndView userProfile(OAuth2AuthenticationToken oAuth2AuthenticationToken, HttpServletRequest request, HttpServletResponse response) {

        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String picture = oAuth2User.getAttribute("picture");

        var userFromDB = repository.findByEmail(email);

        if (userFromDB.isPresent()) {

            Users user = userFromDB.get(); // Recupera o usuário

            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("picture", picture);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("profession", user.getProfession().name());

            String encodedUsername = user.getUsername().replace(" ", "_");
            String encodedId = user.getId().replace(" ", "");

            Cookie userCookie = new Cookie("username", encodedUsername);
            cookieAttributes.setCookieAttributes(userCookie);

            Cookie professionCookie = new Cookie("profession", user.getProfession().name());
            cookieAttributes.setCookieAttributes(professionCookie);

            Cookie pictureCookie = new Cookie("picture", picture);
            cookieAttributes.setCookieAttributes(pictureCookie);

            Cookie idCookie = new Cookie("id", encodedId);
            cookieAttributes.setCookieAttributes(idCookie);

            System.out.println("Username in session: " + request.getSession().getAttribute("picture"));

            response.addCookie(userCookie);
            response.addCookie(pictureCookie);
            response.addCookie(idCookie);
            response.addCookie(professionCookie);

            return new ModelAndView("redirect:/SmartCV");
        } else {
            return new ModelAndView("redirect:/oauth2/authorization/google");
        }

    }
}