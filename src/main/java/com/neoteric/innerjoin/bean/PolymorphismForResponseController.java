package com.neoteric.innerjoin.bean;

import com.neoteric.innerjoin.bean.StudentDatabaseService;
import com.neoteric.innerjoin.polymorphysmforresponse.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/apii")
public class PolymorphismForResponseController {

        @Autowired
        private StudentDatabaseService studentDatabaseService;

        // Accept both JSON and XML
        @PostMapping(
                value = "/studentss",
                consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
                produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
        public String addStudent(@RequestBody com.neoteric.innerjoin.polymorphysmforresponse.Students students) {
            String studentId = studentDatabaseService.insertStudent(students);
            return "Student inserted with ID: " + studentId;
        }

    @GetMapping(value = "/studentss/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Students getStudentById(@PathVariable("id") String studentId) {
        return studentDatabaseService.getStudentById(studentId);
    }
}
