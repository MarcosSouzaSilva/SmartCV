package com.smartcv.smartcv.service.personalInfo;

import com.smartcv.smartcv.dto.PersonalInfoDTO;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.UsersRepository;
import com.smartcv.smartcv.strategy.CookieAttributes;
import com.smartcv.smartcv.strategy.EmailValid;
import com.smartcv.smartcv.strategy.IsInvalidPassword;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ServicePersonalInfo {

    @Autowired
    private EmailValid emailValid;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private CookieAttributes cookieAttributes;

    @Autowired
    private IsInvalidPassword isInvalidPassword;


    public ModelAndView page(@ModelAttribute("loginDto") PersonalInfoDTO infoDTO){
        ModelAndView mv = new ModelAndView("personal_info/personalInfo");
        mv.addObject("personalInfoDTO", infoDTO);

        return mv;
    }

    public ModelAndView send(PersonalInfoDTO infoDTO, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("personal_info/personalInfo");
        Users userInfo = infoDTO.request().getUser();


        return mv;
    }


}
