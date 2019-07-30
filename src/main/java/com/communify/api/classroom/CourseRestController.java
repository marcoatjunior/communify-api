package com.communify.api.classroom;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@RequestMapping("/api/classroom/courses")
@org.springframework.web.bind.annotation.RestController
public class CourseRestController {
    /**
     * Search the courses lists and retrieves to show it
     * @return An Courses lists
     * @throws IOException If had an connection error
     */
    @CrossOrigin(origins = {"http://localhost:8000", "https://localhost:8100", "http://localhost:8200"})
    @GetMapping(value = "", produces = "application/json")
    public StringBuffer listCourses() throws Exception {
        return (new RequestController()).create("https://classroom.googleapis.com/v1/courses?courseStates=ACTIVE");
    }

    /**
     * Search the students registered in a course
     * @return An Students list of a course
     * @throws Exception If had an connection error
     */
    @CrossOrigin(origins = {"http://localhost:8000", "https://localhost:8100", "http://localhost:8200"})
    @GetMapping(value = "/course/students", produces = "application/json")
    public StringBuffer listStudents(@RequestParam String courseId) throws Exception {
        return (new RequestController()).create("https://classroom.googleapis.com/v1/courses/" + courseId + "/students");
    }

    /**
     * Search for the student courses through id
     * @return Students information based on id
     * @throws Exception If had an connection error
     */
    @CrossOrigin(origins = {"http://localhost:8000", "https://localhost:8100", "http://localhost:8200"})
    @GetMapping(value = "/course/tasks", produces = "application/json")
    public String listTasks(@RequestParam String studentId) throws Exception {

        String coursesCreated = this.listCourses().toString();
        WorkRestController work = new WorkRestController();
        TopicRestController topic = new TopicRestController();

        if (coursesCreated.length() > 2) {
            JSONArray courses = new JSONObject(coursesCreated).getJSONArray("courses");
            JSONArray studentCourses = new JSONArray();
            for (int i = 0; i < courses.length(); i++) {
                String studentsInCourse = this.listStudents(courses.getJSONObject(i).getString("id"))
                        .toString();
                if (studentsInCourse.length() > 2) {
                    JSONArray students = new JSONObject(studentsInCourse)
                            .getJSONArray("students");
                    for (int j = 0; j < students.length(); j++) {
                        if (students.getJSONObject(j).getString("userId").equalsIgnoreCase(studentId)) {
                            courses.getJSONObject(i).put("works", new JSONObject(work.listCouseworks(
                                    courses
                                            .getJSONObject(i)
                                            .getString("id"))
                                    .toString()));
                            courses.getJSONObject(i).put("topics", new JSONObject(topic.listTopics(
                                    courses
                                            .getJSONObject(i)
                                            .getString("id"))
                                    .toString()));
                            studentCourses.put(courses.getJSONObject(i));
                        }
                    }
                }
            }

            return studentCourses.toString();
        }

        return new String();
    }
}
