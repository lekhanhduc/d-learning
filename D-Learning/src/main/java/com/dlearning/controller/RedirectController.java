package com.dlearning.controller;

import com.dlearning.entity.Course;
import com.dlearning.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final CourseService courseService;

    @GetMapping("/login")
    public String loginForm() {
//        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
//            return "redirect:/home";
//        }
        return "authentication/login";
    }

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Course> fetchAll = courseService.getAllCourses();
        model.addAttribute("courses", fetchAll);
        return "landing-page/home";
    }

    @GetMapping("/register")
    public String registerForm(){
        return "authentication/register";
    }

    @GetMapping("/addCourse")
    public String addCourseForm(){
        return "admin/add-course";
    }

    @GetMapping("/course-detail")
    public String getCourseDetail(@RequestParam("id") Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "course/course-detail";
    }

    @GetMapping("/shop-cart")
    public String shopCart(){
        return "landing-page/shop-cart";
    }
}
