package com.smartcv.smartcv.service.education;

import com.smartcv.smartcv.dto.EducationDTO;
import com.smartcv.smartcv.model.Education;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.EducationRepository;
import com.smartcv.smartcv.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Service

public class ServiceEducation {

    @Autowired
    private EducationRepository repository;

    @Autowired
    private UsersRepository userRepository;


    public ModelAndView educationPage(@ModelAttribute("educationDTO") EducationDTO dto, HttpServletRequest request) {

        ModelAndView view = new ModelAndView("education/education");

        view.addObject("educationDTO", dto);

        String newUserId = (String) request.getSession().getAttribute("id");

        if (newUserId != null) {
            view.addObject("newUsernameId", newUserId);
        } else {
            System.err.println("User is not logged in. Redirecting to the home page. 2");
            return new ModelAndView("redirect:/SmartCV/login");
        }
        return view;
    }

    public ModelAndView send(EducationDTO infoDTO, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("education/education");

        Education education = infoDTO.request();

        var nowYear = LocalDate.now().getYear(); // ano atual

        String newUsernameId = (String) request.getSession().getAttribute("id");

        if (newUsernameId == null) {
            return new ModelAndView("redirect:/SmartCV");
        } else if (education.getGraduation_year() > nowYear){
            bindingResult.rejectValue("graduation_year", "error.educationDTO", "Date invalid.");
            return mv;
        }

        Users user = userRepository.findById(newUsernameId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        education.setUser(user); // O user_id será preenchido automaticamente pela JPA

        this.repository.save(education);

        return new ModelAndView("redirect:/SmartCV/courses");
    }
}