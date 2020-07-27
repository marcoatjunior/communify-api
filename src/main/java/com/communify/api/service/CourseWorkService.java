package com.communify.api.service;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contracts.IClassroomService;
import com.communify.api.contracts.ICourseWorkService;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.CourseWork;
import com.google.api.services.classroom.model.ListCourseWorkResponse;

import lombok.Getter;

@Service
@Getter
public class CourseWorkService implements ICourseWorkService {

    @Autowired
    private IClassroomService classroomService;
    
    @Override
    public List<CourseWork> list(String accessToken) {
        try {
            Classroom classroom = getClassroomService().instance(accessToken);
            List<Course> coursesList = searchCourses(classroom);
            List<String> coursesIds = mapCourseIds(coursesList);
            List<ListCourseWorkResponse> courseWorkResponseList = mapCourseWorksByCourse(classroom, coursesIds);
            return courseWorksList(courseWorkResponseList);
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
    
    private List<CourseWork> courseWorksList(List<ListCourseWorkResponse> response) {
        List<CourseWork> courseWorksList = response.stream()
            .filter(courseWorksResponse -> !isNull(courseWorksResponse))
            .map(courseWorkResponseList -> courseWorkResponseList.getCourseWork())
            .filter(courseWorks -> !isNull(courseWorks))
            .flatMap(courseWorks -> courseWorks.stream())
            .collect(toList());
        return courseWorksList;
    }
}
