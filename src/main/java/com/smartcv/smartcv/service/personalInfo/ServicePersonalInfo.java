package com.smartcv.smartcv.service.personalInfo;

import com.smartcv.smartcv.dto.PersonalInfoDTO;
import com.smartcv.smartcv.dto.enums.EuaStates;
import com.smartcv.smartcv.dto.enums.Profession;
import com.smartcv.smartcv.dto.enums.Seniority;
import com.smartcv.smartcv.model.PersonalInfo;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.PersonalInfoRepository;
import com.smartcv.smartcv.repository.UsersRepository;
import com.smartcv.smartcv.strategy.EmailValid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    private PersonalInfoRepository repository;

    @Autowired
    private UsersRepository userRepository;


    public ModelAndView page(@ModelAttribute("loginDto") PersonalInfoDTO infoDTO, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("personal_info/personalInfo");

        mv.addObject("personalInfoDTO", infoDTO);
        mv.addObject("eua_statesList", EuaStates.values());
        mv.addObject("seniorityList", Seniority.values());

        String id = (String) request.getSession().getAttribute("id");

        request.getSession().setAttribute("id", id);

        String newUserId = (String) request.getSession().getAttribute("id");

        if (newUserId != null) {
            mv.addObject("newUsernameId", newUserId);
        } else {
            System.err.println("User is not logged in. Redirecting to the home page. 1");
            return new ModelAndView("redirect:/SmartCV");
        }

        return mv;
    }

    public ModelAndView send(@Valid @ModelAttribute("personalInfoDTO") PersonalInfoDTO infoDTO, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("personal_info/personalInfo");

        PersonalInfo userInfo = infoDTO.request();

        String newUsernameId = (String) request.getSession().getAttribute("id");

        var validationAge = userInfo.getAge() > 0;

        var emailValid = this.emailValid.emailValid(userInfo.getEmail());

         if (!emailValid) {

            bindingResult.rejectValue("email", "error.personalInfoDTO", "Email Invalid !");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("eua_statesList", infoDTO.getEua_states());
            mv.addObject("seniorityList", infoDTO.getSeniority());

            return mv;

        } else if (!validationAge) {

            bindingResult.rejectValue("age", "error.personalInfoDTO", "Age Invalid !");
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("eua_statesList", infoDTO.getEua_states());
            mv.addObject("seniorityList", infoDTO.getSeniority());

            return mv;

        }

        Users user = userRepository.findById(newUsernameId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userInfo.setUser(user);

        this.repository.save(userInfo);

        return new ModelAndView("redirect:/SmartCV/education");
    }
}