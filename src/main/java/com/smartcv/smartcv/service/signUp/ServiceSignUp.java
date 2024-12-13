package com.smartcv.smartcv.service.signUp;

import com.smartcv.smartcv.dto.enums.Profession;
import com.smartcv.smartcv.dto.SignUpDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;


@Service
public class ServiceSignUp {

    @Autowired
    private EmailValid emailValid;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder securityConfig;

    @Autowired
    private CookieAttributes cookieAttributes;

    @Autowired
    private IsInvalidPassword isInvalidPassword;


    public ModelAndView signUpPage(@ModelAttribute("dtoRegister") SignUpDTO dto) {

        ModelAndView view = new ModelAndView("signUp/signUp");

        view.addObject("dtoRegister", dto);
        view.addObject("listaStatusUser", Profession.values());


        return view;
    }

    public ModelAndView signUp(@Valid @ModelAttribute("dtoRegister") SignUpDTO registerDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("signUp/signUp");

        Users users = registerDto.request();

        var emailValid = this.emailValid.emailValid(users.getEmail());

        var passwordInvalid = isInvalidPassword.verificationOfPassword(users.getPassword());

        var emailExist = repository.findByEmail(users.getEmail());


        if (bindingResult.hasErrors()) {
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", registerDto.getProfession());
            return mv;

        } else if (emailExist.isPresent()) {
            bindingResult.rejectValue("email", "error.dtoRegister", "There is already a user with the email !");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", registerDto.getProfession());

            return mv;

        } else if (!emailValid) {
            bindingResult.rejectValue("email", "error.dtoRegister", "Email Invalid !");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", registerDto.getProfession());

            return mv;

        } else if (passwordInvalid) {
            bindingResult.rejectValue("password", "error.loginDto", "☑ Your password need have at least 8 to 20 character");
            bindingResult.rejectValue("password", "error.loginDto", "☑ Supper and lower case letters and symbols");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", registerDto.getProfession());

            return mv;

        } else {

            try {

                users.setPassword(securityConfig.encode(users.getPassword()));

                this.repository.save(users);

                var user = repository.findByEmail(users.getEmail()).get();

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
        }
    }
}