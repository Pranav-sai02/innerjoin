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

    // Show the home page with student ID input form (GET)
    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This will resolve to home.html
    }

    // Handle student ID submission and display student details (POST)
    @PostMapping("/students/view")
    public String showStudent(@RequestParam("studentId") String studentId, Model model) {
        Students student = studentDatabaseService.getStudentById(studentId);

        if (student != null) {
            model.addAttribute("student", student);
            return "student"; // This will resolve to student.html
        } else {
            model.addAttribute("error", "Student not found with ID: " + studentId);
            return "home"; // Redirect to home page
        }
    }
}
