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
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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

    public ModelAndView login(@ModelAttribute("loginDto") LoginDTO loginDto) {
        ModelAndView modelAndView = new ModelAndView("login/login");

        /*oauth2/authorization/google*/

        modelAndView.addObject("loginDto", loginDto);

        return modelAndView;
    }


    public ModelAndView sendLogin(@Valid @ModelAttribute("loginDto") LoginDTO loginDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("login/login");

        Users users = loginDto.request();

        String plainPassword = users.getPassword();

        var emailInvalid = emailValid.emailValid(users.getEmail());

        var userFromDB = repository.findByEmail(users.getEmail());   // Busque o usuário pelo email fornecido no login

        var passwordInvalid = isInvalidPassword.verificationOfPassword(users.getPassword());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (bindingResult.hasErrors()) {
            return mv;

        } else if (!emailInvalid) {
            bindingResult.rejectValue("email", "error.loginDto", "Invalid !");
            System.out.println(users.getEmail());
            return mv;

        } else if (passwordInvalid) {
            bindingResult.rejectValue("password", "error.loginDto", "⬛ Your password need have at least 8 to 20 character.");
            bindingResult.rejectValue("password", "error.loginDto", "⬛ Supper and lower case letters and symbols.");
            System.out.println(users.getEmail());
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

                    String encodedUsername = user.getUsername().replace(" ", "_");
                    String encodedId = user.getId().replace(" ", "");

                    Cookie userCookie = new Cookie("username", encodedUsername);
                    cookieAttributes.setCookieAttributes(userCookie);

                    Cookie professionCookie = new Cookie("profession", user.getProfession().name());
                    cookieAttributes.setCookieAttributes(professionCookie);

                    Cookie idCookie = new Cookie("id", encodedId);
                    cookieAttributes.setCookieAttributes(idCookie);

                    response.addCookie(userCookie);
                    response.addCookie(idCookie);
                    response.addCookie(professionCookie);

                    return new ModelAndView("redirect:/SmartCV");

                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred, try it again");
                    return null;
                }
            }else {
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