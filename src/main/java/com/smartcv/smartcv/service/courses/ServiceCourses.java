package com.smartcv.smartcv.service.courses;

import com.smartcv.smartcv.dto.CoursesDTO;
import com.smartcv.smartcv.model.Courses;
import com.smartcv.smartcv.model.Users;
import com.smartcv.smartcv.repository.CoursesRepository;
import com.smartcv.smartcv.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Service

public class ServiceCourses {

    @Autowired
    private CoursesRepository repository;

    @Autowired
    private UsersRepository userRepository;


    public ModelAndView coursesPage(@ModelAttribute("educationDTO") CoursesDTO dto, HttpServletRequest request) {

        ModelAndView view = new ModelAndView("courses/courses");

        view.addObject("coursesDTO", dto);

        String newUserId = (String) request.getSession().getAttribute("id");

        if (newUserId != null) {
            view.addObject("newUsernameId", newUserId);
        } else {
            System.err.println("User is not logged in. Redirecting to the home page. 2");
            return new ModelAndView("redirect:/SmartCV/login");
        }
        return view;
    }

    public ModelAndView send(CoursesDTO infoDTO, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("courses/courses");

        Courses courses = infoDTO.request();

        var nowYear = LocalDate.now().getYear(); // ano atual

        String newUsernameId = (String) request.getSession().getAttribute("id");

        if (newUsernameId == null) {
            return new ModelAndView("redirect:/SmartCV");
        } else if (courses.getGraduation_year() > nowYear){
            bindingResult.rejectValue("graduation_year", "error.coursesDTO", "Date invalid.");
            return mv;
        }

        Users user = userRepository.findById(newUsernameId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        courses.setUser(user); // O user_id será preenchido automaticamente pela JPA

        this.repository.save(courses);

        return new ModelAndView("redirect:/SmartCV");
    }
}