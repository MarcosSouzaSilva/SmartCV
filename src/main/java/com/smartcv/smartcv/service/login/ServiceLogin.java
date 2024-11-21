package com.smartcv.smartcv.service.login;

import com.smartcv.smartcv.dto.LoginDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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

    public ModelAndView login(@ModelAttribute("loginDto") LoginDTO loginDto) {
        ModelAndView modelAndView = new ModelAndView("login/login");

        modelAndView.addObject("loginDto", loginDto);

        return modelAndView;
    }


    public ModelAndView sendLogin(@Valid @ModelAttribute("loginDto") LoginDTO loginDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("login/login");

        Users users = loginDto.request();

        String plainPassword = users.getPassword();

        var emailInvalid = emailValid.validation(users);

        var userFromDB = repository.findByEmail(users.getEmail());   //  Busque o usuário pelo email fornecido no login

        var passwordInvalid = isInvalidPassword.validation(users);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (bindingResult.hasErrors()) {
            return mv;

        } else if (!emailInvalid) {
            bindingResult.rejectValue("email", "error.loginDto", "Invalid !");
            return mv;

        } else if (passwordInvalid) {
            bindingResult.rejectValue("password", "error.loginDto", "⬛ Your password need have at least 8 to 20 character.");
            bindingResult.rejectValue("password", "error.loginDto", "⬛ Supper and lower case letters and symbols.");
            return mv;
        }

        if (userFromDB.isPresent()) { // Verifica se o usuário foi encontrado
            // Recupere o hash da senha armazenado no banco de dados
            String hashedPasswordFromDB = userFromDB.get().getPassword();

            // Comparando a senha fornecida com o hash da senha armazenado
            if (passwordEncoder.matches(plainPassword, hashedPasswordFromDB)) {
                // Autenticação bem-sucedida
                Users user = userFromDB.get(); // Recupera o usuário

                try {

                    request.getSession().setAttribute("username", user.getUsername());
                    request.getSession().setAttribute("id", user.getId());
                    request.getSession().setAttribute("profession", user.getProfession().name());

                    Cookie userCookie = new Cookie("username", user.getUsername());
                    cookieAttributes.setCookieAttributes(userCookie);

                    Cookie professionCookie = new Cookie("profession", user.getProfession().name());
                    cookieAttributes.setCookieAttributes(professionCookie);
                    System.err.println("1");

                    Cookie userCookieId = new Cookie("id", user.getId());
                    cookieAttributes.setCookieAttributes(userCookieId);

                    System.err.println("2");

                    response.addCookie(userCookie);
                    response.addCookie(userCookieId);
                    response.addCookie(professionCookie);

                    System.err.println("12");

                    return new ModelAndView("redirect:/SmartCV");

                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred, try it again");
                    return null;
                }
            } else {
                bindingResult.rejectValue("password", "error.loginDto", "User or password invalid, try again.");
                return mv;
            }
        } else {
            bindingResult.rejectValue("email", "error.loginDto", "This email doesn't match any account.");
            bindingResult.rejectValue("email", "error.loginDto", " Please try again or create a new account.");
        }
        return mv;
    }
}