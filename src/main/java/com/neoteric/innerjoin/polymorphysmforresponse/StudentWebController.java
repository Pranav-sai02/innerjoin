package com.neoteric.innerjoin.polymorphysmforresponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentWebController {

    @Autowired
    private StudentDatabaseService studentDatabaseService;

    // Show the home page with student ID
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    // display student details
    @PostMapping("/students/view")
    public String showStudent(@RequestParam("studentId") String studentId, Model model) {
        Students student = studentDatabaseService.getStudentById(studentId);

        if (student != null) {
            model.addAttribute("student", student);
            return "student";
        } else {
            model.addAttribute("error", "Student not found with ID: " + studentId);
            return "home";
        }
    }
}
