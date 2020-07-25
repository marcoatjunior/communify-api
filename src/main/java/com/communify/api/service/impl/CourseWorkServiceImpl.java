package com.communify.api.service.impl;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.service.ClassroomService;
import com.communify.api.service.CourseWorkService;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.CourseWork;
import com.google.api.services.classroom.model.ListCourseWorkResponse;

@Service
public class CourseWorkServiceImpl implements CourseWorkService {

    @Autowired
    ClassroomService classroomService;
    
    @Override
    public List<CourseWork> list(String accessToken) {
        try {
            Classroom classroom = classroomService.instance(accessToken);
            List<Course> listCourses = searchCourses(classroom);
            List<String> courseIds = mapCourseIds(listCourses);
            List<ListCourseWorkResponse> listCourseWorkResponse = mapCourseWorksByCourse(classroom, courseIds);
            return listCouseWorks(listCourseWorkResponse);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ArrayList<CourseWork>();
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
            .filter(courseWorksResponse -> !isNull(courseWorksResponse))
            .map(listCourseWorkResponse -> listCourseWorkResponse.getCourseWork())
            .filter(courseWorks -> !isNull(courseWorks))
            .flatMap(courseWorks -> courseWorks.stream())
            .collect(toList());
        return listCourseWorks;
    }
}
