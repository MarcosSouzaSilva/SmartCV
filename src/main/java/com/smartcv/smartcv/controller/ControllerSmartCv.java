package com.smartcv.smartcv.controller;

import com.smartcv.smartcv.dto.LoginDTO;
import com.smartcv.smartcv.dto.PerfilDTO;
import com.smartcv.smartcv.dto.SignUpDTO;
import com.smartcv.smartcv.dto.PersonalInfoDTO;
import com.smartcv.smartcv.service.index.ServiceIndex;
import com.smartcv.smartcv.service.login.ServiceLogin;
import com.smartcv.smartcv.service.personalInfo.ServicePersonalInfo;
import com.smartcv.smartcv.service.profile.ServicePerfil;
import com.smartcv.smartcv.service.signUp.ServiceSignUp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/SmartCV")
public class ControllerSmartCv {

    @Autowired
    private ServiceSignUp service;

    @Autowired
    private ServiceIndex serviceIndex;

    @Autowired
    private ServiceLogin serviceLogin;

    @Autowired
    private ServicePerfil servicePerfil;

    @Autowired
    private ServicePersonalInfo servicePersonalInfo;

    @GetMapping
    public ModelAndView index(HttpServletRequest request) {
        return serviceIndex.index(request);
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("loginDto") LoginDTO loginDto) {
        return serviceLogin.login(loginDto);
    }

    @GetMapping("/personalInfo")
    public ModelAndView personalInfo(@ModelAttribute("loginDto") PersonalInfoDTO infoDTO) {
        return servicePersonalInfo.page(infoDTO);
    }

    @PostMapping("/personalInfo")
    public ModelAndView personalInfo(@ModelAttribute("loginDto") PersonalInfoDTO infoDTO, BindingResult bindingResult, HttpServletRequest request) {
        return servicePersonalInfo.send(infoDTO, bindingResult, request);
    }

    @GetMapping("/signUp")
    public ModelAndView signUp(@ModelAttribute("dtoRegister") SignUpDTO dto) {
        return service.signUpPage(dto);
    }

    @GetMapping("/profiles")
    public ModelAndView perfil(@ModelAttribute("dtoRegister") PerfilDTO dto) {
        return servicePerfil.page(dto);
    }

    @PostMapping("/signUp")
    public ModelAndView register(@Valid @ModelAttribute("dtoRegister") SignUpDTO dtoRegister, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return service.signUp(dtoRegister, bindingResult, request, response);
    }

    @PostMapping("/login")
    public ModelAndView loginSend(@Valid @ModelAttribute("loginDto") LoginDTO loginDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return serviceLogin.sendLogin(loginDto, bindingResult, request, response);
    }

    @GetMapping("/profile")
    public ModelAndView perfil(@RequestParam(name = "id") String id, @ModelAttribute("perfilDto") PerfilDTO perfilDto, HttpServletRequest request) {
        return servicePerfil.pageAndInfo(id, perfilDto, request);
    }

    @PostMapping("/profile")
    public ModelAndView update(@Valid @ModelAttribute("perfilDto") PerfilDTO perfilDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return servicePerfil.update(perfilDto, bindingResult, request, response);
    }

   /* // Endpoint para capturar informações do usuário após o login com Google
    @GetMapping("/user")
    public ModelAndView user(@AuthenticationPrincipal OAuth2User principal) {
        // Obtém o nome e o email do usuário autenticado via Google
        String name = principal.getAttribute("username");
        String email = principal.getAttribute("email");

        // Exibe uma página com as informações do usuário
        ModelAndView modelAndView = new ModelAndView("userProfile"); // Crie uma view "userProfile" para exibir os dados
        modelAndView.addObject("username", name);
        modelAndView.addObject("email", email);
        return modelAndView;
    }*/
}
