package com.smartcv.smartcv.service.profile;


import com.smartcv.smartcv.dto.PerfilDTO;
import com.smartcv.smartcv.dto.enums.Profession;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.UsersRepository;
import com.smartcv.smartcv.strategy.CookieAttributes;
import com.smartcv.smartcv.strategy.EmailValid;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Service
public class ServicePerfil {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private EmailValid emailValid;

    @Autowired
    private CookieAttributes cookieAttributes;

    public ModelAndView page(@ModelAttribute("dtoRegister") PerfilDTO dto) {
        ModelAndView mv = new ModelAndView("profile/profile");

        mv.addObject("perfilDto", dto);
        mv.addObject("listaStatusUser", Profession.values());

        return mv;
    }

    public ModelAndView pageAndInfo(@RequestParam(name = "id") String id, @ModelAttribute("perfilDto") PerfilDTO perfilDto, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("profile/profile");


        var optionalCadastro = repository.findById(id);

        String newUsernameId = (String) request.getSession().getAttribute("id");
        String userIdFromSession = (String) request.getSession().getAttribute("id");
        String newUsername = (String) request.getSession().getAttribute("username");
        String newUsernameProfession = (String) request.getSession().getAttribute("profession");

        var infoForUserNull = newUsernameId == null && newUsername == null && newUsernameProfession == null && !userIdFromSession.equals(id) ;

        if (infoForUserNull) {
            System.err.println("User is not logged in. Redirecting to the home page.");
            return new ModelAndView("redirect:/SmartCV/login");
        }

        if (optionalCadastro.isPresent()) {

            Users user = optionalCadastro.get();

            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", user.getProfession());

            perfilDto.fromDtoCadastro(user);

            mv.addObject("perfilDto", perfilDto);
            mv.addObject("newUsernameId", newUsernameId);
            mv.addObject("newUsername", newUsername);
            mv.addObject("newUsernameProfession", newUsernameProfession);

        } else {
            System.err.println("Id not found");
        }
        return mv;
    }

    public ModelAndView update(@Valid @ModelAttribute("perfilDto") PerfilDTO dto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("profile");

        Users users = dto.request();

        mv.addObject("perfilDto", dto);

        boolean invalidEmail = emailValid.validation(users);

        if (bindingResult.hasErrors()) {
            System.err.println("There was a bindingResult error");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", users.getProfession());
            return mv;
        }

        if (!invalidEmail) {
            bindingResult.rejectValue("email", "error.perfilDto", "Invalid email!");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("selectedProfession", users.getProfession());
            return mv;
        }

        Cookie[] cookies = request.getCookies();
        String userId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("id".equals(cookie.getName())) {
                    userId = cookie.getValue();
                }
            }
        }

        if (userId != null) {

            var id = repository.findById(userId);

            if (id.isPresent()) {

                Users user = id.get();

                if (!user.getEmail().equals(users.getEmail()) && repository.findByEmail(users.getEmail()).isPresent()) {
                    bindingResult.rejectValue("email", "error.perfilDto", "The email already exists");
                    mv.addObject("listaStatusUser", Profession.values());
                    mv.addObject("selectedProfession", users.getProfession());
                    return mv;
                }

                boolean update = false;

                if (!user.getUsername().equals(users.getUsername())) {
                    user.setUsername(users.getUsername());
                    update = true;
                }

                if (!user.getEmail().equals(users.getEmail())) {
                    user.setEmail(users.getEmail());
                    update = true;
                }

                if (!user.getProfession().equals(users.getProfession())) {
                    user.setProfession(users.getProfession());
                    update = true;
                }

                if (update) {

                    repository.save(user);

                    request.getSession().setAttribute("username", user.getUsername());
                    request.getSession().setAttribute("profession", user.getProfession().name());
                    request.getSession().setAttribute("id", user.getId());

                    Cookie userCookie = new Cookie("username", user.getUsername());
                    cookieAttributes.setCookieAttributes(userCookie);

                    Cookie professionCookie = new Cookie("profession", user.getProfession().name());
                    cookieAttributes.setCookieAttributes(professionCookie);

                    Cookie idCookie = new Cookie("id", user.getId());
                    cookieAttributes.setCookieAttributes(idCookie);

                    response.addCookie(idCookie);
                    response.addCookie(userCookie);
                    response.addCookie(professionCookie);

                    return new ModelAndView("redirect:/SmartCV");
                }
            }
        } else {
            System.out.println(users.getEmail());
            System.err.println("Valor não presente");
        }
        return new ModelAndView("redirect:/SmartCV");
    }
}