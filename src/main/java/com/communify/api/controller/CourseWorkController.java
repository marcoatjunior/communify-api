package com.communify.api.rest;

import static com.google.api.client.auth.oauth2.BearerToken.authorizationHeaderAccessMethod;
import static com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport;
import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.dto.CourseWorkDTO;
import com.communify.api.mapper.CourseWorkMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.CourseWork;
import com.google.api.services.classroom.model.ListCourseWorkResponse;

@RestController
@RequestMapping("/courseWorks")
public class CourseWorkRest {

    @CrossOrigin
    @GetMapping(produces = "application/json")
    public List<CourseWorkDTO> list(@RequestHeader("Authorization") String accessToken) throws Exception {
        Classroom service = instanceServiceWithCredentials(accessToken);
        List<Course> listCourses = searchCourses(service);
        List<String> courseIds = mapCourseIds(listCourses);
        List<ListCourseWorkResponse> listCourseWorkResponse = mapCourseWorksByCourse(service, courseIds);
        List<CourseWork> listCourseWorks = listCouseWorks(listCourseWorkResponse);
        return CourseWorkMapper.modelsToDTOs(listCourseWorks);
    }
    
    private Classroom instanceClassroomService(Credential credential, final NetHttpTransport httpTransport) {
        Classroom service = new Classroom.Builder(httpTransport, getDefaultInstance(), credential).build();
        return service;
    }

    private Classroom instanceServiceWithCredentials(String accessToken) throws GeneralSecurityException, IOException {
        Credential credential = new Credential(authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        final NetHttpTransport httpTransport = newTrustedTransport();
        Classroom service = instanceClassroomService(credential, httpTransport);
        return service;
    }
    
    private List<Course> searchCourses(Classroom service) throws IOException {
        return service.courses().list().execute().getCourses();
    }
    
    private List<String> mapCourseIds(List<Course> listCourses) {
        return listCourses.stream().map(course -> course.getId()).collect(toList());
    }

    private List<ListCourseWorkResponse> mapCourseWorksByCourse(Classroom service, List<String> courseIds) {
        return courseIds.stream().map(id -> searchCourseWorksByCourseId(service, id)).collect(toList());
    }

    private ListCourseWorkResponse searchCourseWorksByCourseId(Classroom service, String id) {
        try {
            return service.courses().courseWork().list(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ListCourseWorkResponse();
    }
    
    private List<CourseWork> listCouseWorks(List<ListCourseWorkResponse> response) {
        List<CourseWork> listCourseWorks = response.stream()
            .filter(courseWorksResponse -> !Objects.isNull(courseWorksResponse))
            .map(listCourseWorkResponse -> listCourseWorkResponse.getCourseWork())
            .filter(courseWorks -> !Objects.isNull(courseWorks))
            .flatMap(courseWorks -> courseWorks.stream())
            .collect(toList());
        return listCourseWorks;
    }
}
