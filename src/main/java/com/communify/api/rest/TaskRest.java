package com.communify.api.rest;

import static com.google.api.client.auth.oauth2.BearerToken.authorizationHeaderAccessMethod;
import static com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport;
import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.ListCoursesResponse;

@RestController
@RequestMapping("/tasks")
public class TaskRest {

    private static final String APPLICATION_NAME = "Communify API";

    @CrossOrigin
    @GetMapping(produces = "application/json")
    public List<Course> list(@RequestHeader("Authorization") String accessToken) throws Exception {
        Credential credential = new Credential(authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        final NetHttpTransport httpTransport = newTrustedTransport();
        Classroom service = new Classroom.Builder(httpTransport, getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();
        ListCoursesResponse response = service.courses().list().execute();
        return response.getCourses();
    }
}
