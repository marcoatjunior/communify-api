package com.communify.api.classroom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/classroom/courses/course/works")
@org.springframework.web.bind.annotation.RestController
public class WorkRestController {
    /**
     * Search the courseWorks of the course
     * @return a list of courseWorks
     * @throws Exception If had an connection error
     */
    @CrossOrigin(origins = {"http://localhost:8000", "https://localhost:8100", "http://localhost:8200"})
    @GetMapping(value = "", produces = "application/json")
    public StringBuffer listCouseworks(@RequestParam String courseId) throws Exception {
        return (new RequestController()).create("https://classroom.googleapis.com/v1/courses/" + courseId + "/courseWork");
    }

    /**
     * Search the courseWorks of the course
     * @return a list of courseWorks
     * @throws Exception If had an connection error
     */
    @CrossOrigin(origins = {"http://localhost:8000", "https://localhost:8100", "http://localhost:8200"})
    @GetMapping(value = "/work", produces = "application/json")
    public StringBuffer getCoursework(@RequestParam String courseId, @RequestParam String workId) throws Exception {
        return (new RequestController()).create("https://classroom.googleapis.com/v1/courses/" + courseId + "/courseWork/" + workId);
    }

}
