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

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue(); // Pega o nome do usuário armazenado no cookie
                } else if ("profession".equals(cookie.getName())) {
                    profession = cookie.getValue();
                }
                if ("id".equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null && !cookieValue.equals("null")) {
                        try {
                            id = cookieValue; // Converte e atribui o valor à variável id
                        } catch (NumberFormatException e) {
                            System.err.println("Error converting ID from cookie: " + e.getMessage());
                        }
                    }
                }
            }
        }
        if (username != null && profession != null) {

            System.out.println("---------------- O que foi salvo na sessão -------------------");
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("id", id);
            request.getSession().setAttribute("profession", profession);

            System.out.println("Nome de usuário salvo na sessão: " + username);
            System.out.println("Id de usuário salvo na sessão: " + id);
            System.out.println("Profession de usuário salvo na sessão: " + profession);

            String newUser = (String) request.getSession().getAttribute("username");
            String newUserId = (String) request.getSession().getAttribute("id");
            Profession newUserProfession = Profession.valueOf(profession);

            if (newUser != null && newUserId != null) {
                modelAndView.addObject("newUsername", newUser);
                modelAndView.addObject("newUsernameId", newUserId);
                modelAndView.addObject("newUserProfession", newUserProfession);
            }
            return modelAndView;
        }
        return modelAndView;
    }
}