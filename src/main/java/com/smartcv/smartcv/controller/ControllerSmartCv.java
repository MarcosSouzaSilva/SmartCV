package com.smartcv.smartcv.controller;

import com.smartcv.smartcv.dto.*;
import com.smartcv.smartcv.service.courses.ServiceCourses;
import com.smartcv.smartcv.service.education.ServiceEducation;
import com.smartcv.smartcv.service.index.ServiceIndex;
import com.smartcv.smartcv.service.login.ServiceLogin;
import com.smartcv.smartcv.service.personalInfo.ServicePersonalInfo;
import com.smartcv.smartcv.service.profile.ServicePerfil;
import com.smartcv.smartcv.service.signUp.ServiceSignUp;
import com.smartcv.smartcv.service.userProfile.ServiceUserProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;;

import javax.script.ScriptException;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/SmartCV")
public class ControllerSmartCv {

    @Autowired
    private ServiceSignUp serviceSignUp;

    @Autowired
    private ServiceIndex serviceIndex;

    @Autowired
    private ServiceUserProfile userProfile;

    @Autowired
    private ServiceEducation serviceEducation;

    @Autowired
    private ServiceLogin serviceLogin;

    @Autowired
    private ServicePerfil servicePerfil;


    @Autowired
    private ServiceCourses serviceCourses;

    @Autowired
    private ServicePersonalInfo servicePersonalInfo;

    @GetMapping
    public ModelAndView index(HttpServletRequest request)  {
        return serviceIndex.index(request);
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("loginDto") LoginDTO loginDto) {
        return serviceLogin.login(loginDto);
    }

    @GetMapping("/personalInfo")
    public ModelAndView personalInfo(@ModelAttribute("personalInfoDTO") PersonalInfoDTO infoDTO, HttpServletRequest request) {
        return servicePersonalInfo.page(infoDTO, request);
    }

    @GetMapping("/education")
    public ModelAndView education(@ModelAttribute("educationDTO") EducationDTO educationDTO, HttpServletRequest request) {
        return serviceEducation.educationPage(educationDTO, request);
    }

    @GetMapping("/courses")
    public ModelAndView courses(@ModelAttribute("coursesDTO") CoursesDTO coursesDTO, HttpServletRequest request) {
        return serviceCourses.coursesPage(coursesDTO, request);
    }

    @PostMapping("/courses")
    public ModelAndView coursesSend(@ModelAttribute("coursesDTO") CoursesDTO coursesDTO, BindingResult bindingResult, HttpServletRequest request) {
        return serviceCourses.send(coursesDTO, bindingResult, request);
    }

    @GetMapping("/terms&Conditions")
    public ModelAndView education() {
        ModelAndView mv = new ModelAndView("terms&Conditions/terms&Conditions");
        return mv;
    }

    @PostMapping("/education")
    public ModelAndView education(@ModelAttribute("educationDTO") EducationDTO educationDTO, BindingResult bindingResult, HttpServletRequest request) {
        return serviceEducation.send(educationDTO, bindingResult, request);
    }

    @PostMapping("/personalInfo")
    public ModelAndView personalInfo(@ModelAttribute("personalInfoDTO") PersonalInfoDTO infoDTO, BindingResult bindingResult, HttpServletRequest request) {
        return servicePersonalInfo.send(infoDTO, bindingResult, request);
    }

    @GetMapping("/signUp")
    public ModelAndView signUp(@ModelAttribute("dtoRegister") SignUpDTO dto) {
        return serviceSignUp.signUpPage(dto);
    }

    @GetMapping("/profiles")
    public ModelAndView perfil(@ModelAttribute("dtoRegister") ProfileDTO dto) {
        return servicePerfil.page(dto);
    }

    @PostMapping("/signUp")
    public ModelAndView register(@Valid @ModelAttribute("dtoRegister") SignUpDTO dtoRegister, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return serviceSignUp.signUp(dtoRegister, bindingResult, request, response);
    }

    @PostMapping("/login")
    public ModelAndView loginSend(@Valid @ModelAttribute("loginDto")  LoginDTO loginDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return serviceLogin.sendLogin(loginDto, bindingResult, request, response);
    }

    @GetMapping("/profile")
    public ModelAndView perfil(@RequestParam(name = "id") String id, @ModelAttribute("perfilDto") ProfileDTO perfilDto, HttpServletRequest request) {
        return servicePerfil.pageAndInfo(id, perfilDto, request);
    }

    @PostMapping("/profile")
    public ModelAndView update(@Valid @ModelAttribute("perfilDto") ProfileDTO perfilDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return servicePerfil.update(perfilDto, bindingResult, request, response);
    }

    @GetMapping("/userProfile")
    public ModelAndView userProfile (OAuth2AuthenticationToken oAuth2AuthenticationToken, HttpServletRequest request, HttpServletResponse response){
        return userProfile.userProfile(oAuth2AuthenticationToken, request, response);
    }


}