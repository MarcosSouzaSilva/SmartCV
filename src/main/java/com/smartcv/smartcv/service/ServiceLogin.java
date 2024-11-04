package com.smartcv.smartcv.service;

import com.smartcv.smartcv.dto.LoginDto;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.UsersRepository;
import com.smartcv.smartcv.strategy.CookieAttributes;
import com.smartcv.smartcv.strategy.EmailValid;
import com.smartcv.smartcv.strategy.IsInvalidPassword;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@Service
public class ServiceLogin {


    @Autowired
    private UsersRepository repository;

    @Autowired
    private EmailValid emailValid;

    @Autowired
    private IsInvalidPassword isInvalidPassword;

    @Autowired
    private CookieAttributes cookieAttributes;

    @Autowired
    private PasswordEncoder securityConfig;


    public ModelAndView login(@ModelAttribute("loginDto") LoginDto loginDto) {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("loginDto", loginDto);

        return modelAndView;
    }


    public ModelAndView sendLogin(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("login");

        Users users = loginDto.request();

        var emailInvalid = emailValid.validation(users);

        var passwordInvalid = isInvalidPassword.validation(users);

        System.out.println("Senha do cara la " + users.getPassword());

        var verificationEmailAndPassword = repository.findByEmailAndPassword(users.getEmail(), users.getPassword());

        if (bindingResult.hasErrors()) {
            return mv;
        }

        if (!emailInvalid) {
            bindingResult.rejectValue("email", "error.loginDto", "Invalid !");
            return mv;

        } else if (passwordInvalid) {
            bindingResult.rejectValue("password", "error.loginDto", "⬛ Your password need have at least 8 to 20 character.");
            bindingResult.rejectValue("password", "error.loginDto", "⬛ Supper and lower case letters and symbols.");
            return mv;
        }

        if (verificationEmailAndPassword.isPresent()) {

            if (users.getEmail() != null && users.getPassword() != null) {

                    Users user = verificationEmailAndPassword.get(); // Recupera o usuário autenticado após validação bem-sucedida de email e senha e username

                    try {

                        request.getSession().setAttribute("username", user.getUsername());
                        request.getSession().setAttribute("profession", user.getProfession().name());
                        request.getSession().setAttribute("id", user.getId());

                        Cookie userCookie = new Cookie("username", user.getUsername());
                        cookieAttributes.setCookieAttributes(userCookie);

                        Cookie userCookieProfession = new Cookie("profession", user.getProfession().name());
                        cookieAttributes.setCookieAttributes(userCookieProfession);

                        Cookie userCookieId = new Cookie("id", user.getId());
                        cookieAttributes.setCookieAttributes(userCookieId);

                        response.addCookie(userCookie);
                        response.addCookie(userCookieId);
                        response.addCookie(userCookieProfession);

                        return new ModelAndView("redirect:/SmartCV");

                    } catch (Exception e) {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred, try it again");
                        return null;
                    }
                }
            }

        } else {
            bindingResult.rejectValue("password", "error.loginDto", "User or password invalid, try again.");
            return mv;
        }
        return mv;
    }
}