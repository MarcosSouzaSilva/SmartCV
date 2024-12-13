package com.smartcv.smartcv.service.index;

import com.smartcv.smartcv.dto.enums.Profession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ServiceIndex {



    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index/index");

        String username = null;
        String id = null;
        String profession = null;
        String picture = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue().replace("_", " "); // Corrigindo o formato ao pegar o valor
                }
                if ("profession".equals(cookie.getName())) {
                    profession = cookie.getValue();
                }
                if ("id".equals(cookie.getName())) {
                    id = cookie.getValue();
                }
                if ("picture".equals(cookie.getName())) {
                    picture = cookie.getValue();
                }
            }
        }


        if (username != null && profession != null) {

            System.out.println("---------------- O que foi salvo na sessão -------------------");

            System.out.println("Nome de usuário salvo na sessão: " + username);
            System.out.println("Id de usuário salvo na sessão: " + id);
            System.out.println("Picture de usuário salvo na sessão: " + picture);
            System.out.println("Profession de usuário salvo na sessão: " + profession);

            if (username != null && profession != null) {
                modelAndView.addObject("newUsername", username); // Passando o valor correto para a view
                modelAndView.addObject("newPicture", picture); // Passando o valor correto para a view
                modelAndView.addObject("newUserProfession", Profession.valueOf(profession));
                modelAndView.addObject("newUsernameId", id);
            }
            return modelAndView;
        }
        return modelAndView;
    }
}