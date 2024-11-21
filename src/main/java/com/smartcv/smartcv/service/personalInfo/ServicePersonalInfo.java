package com.smartcv.smartcv.service.personalInfo;

import com.smartcv.smartcv.dto.PersonalInfoDTO;
import com.smartcv.smartcv.dto.enums.EuaStates;
import com.smartcv.smartcv.dto.enums.Gender;
import com.smartcv.smartcv.dto.enums.MaritalStatus;
import com.smartcv.smartcv.dto.enums.Profession;
import com.smartcv.smartcv.model.PersonalInfo;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.PersonalInfoRepository;
import com.smartcv.smartcv.repository.UsersRepository;
import com.smartcv.smartcv.strategy.CookieAttributes;
import com.smartcv.smartcv.strategy.EmailValid;
import com.smartcv.smartcv.strategy.IsInvalidPassword;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private CookieAttributes cookieAttributes;

    @Autowired
    private IsInvalidPassword isInvalidPassword;


    public ModelAndView page(@ModelAttribute("loginDto") PersonalInfoDTO infoDTO) {
        ModelAndView mv = new ModelAndView("personal_info/personalInfo");

        mv.addObject("personalInfoDTO", infoDTO);
        mv.addObject("eua_statesList", EuaStates.values());
        mv.addObject("genderList", Gender.values());
        mv.addObject("maritalStatusList", MaritalStatus.values());

        return mv;
    }

    public ModelAndView send(PersonalInfoDTO infoDTO, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("personal_info/personalInfo");

        PersonalInfo userInfo = infoDTO.request();

        boolean userIsNotNull = userInfo != null && userInfo.getAddress() != null;

        if (bindingResult.hasErrors()) {
            mv.addObject("listaStatusUser", Profession.values());
            mv.addObject("maritalStatusList", infoDTO.getMaritalStatus());
            mv.addObject("eua_statesList", infoDTO.getEua_states());
            mv.addObject("genderList", infoDTO.getGender());
            return mv;

        } else if (userIsNotNull) {

            String newUsernameId = (String) request.getSession().getAttribute("id");

            if (newUsernameId == null) {
                return new ModelAndView("redirect:/SmartCV");
            }

            Users user = userRepository.findById(newUsernameId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            userInfo.setUser(user); // O user_id será preenchido automaticamente pela JPA

            this.repository.save(userInfo);


            return new ModelAndView("redirect:/SmartCV");
        }

        mv.addObject("listaStatusUser", Profession.values());
        mv.addObject("maritalStatusList", infoDTO.getMaritalStatus());
        mv.addObject("eua_statesList", infoDTO.getEua_states());
        mv.addObject("genderList", infoDTO.getGender());
        mv.addObject("error", "Endereço não pode ser vazio.");

        return mv;
    }


}
