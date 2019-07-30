package com.communify.api.classroom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@RequestMapping("/api/classroom/userProfile")
@org.springframework.web.bind.annotation.RestController
public class UserRestController {
    /**
     * Search for the student's profile finding by email as id
     * @return Students information based on email
     * @throws IOException If had an connection error
     */
    @CrossOrigin(origins = {"http://localhost:8000", "https://localhost:8100", "http://localhost:8200"})
    @GetMapping(value = "", produces = "application/json")
    public StringBuffer findStudentByEmail(@RequestParam String email) throws Exception {
        return (new RequestController()).create("https://classroom.googleapis.com/v1/userProfiles/" + email);
    }
}
