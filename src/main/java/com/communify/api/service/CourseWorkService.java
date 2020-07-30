package com.communify.api.service;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.ClassroomDateHelper.toLocalDate;
import static com.communify.api.helper.DateHelper.isGreaterOrEqualThanNow;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contract.IClassroomService;
import com.communify.api.contract.ICourseWorkService;
import com.communify.api.model.CourseWork;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.ListCourseWorkResponse;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class CourseWorkService implements ICourseWorkService {
    
    @Autowired
    private IClassroomService classroomService;
    
    @Override
    public List<CourseWork> list(String accessToken) {
        try {
            Classroom classroom = getClassroomInstance(accessToken);
            List<Course> coursesList = searchCourses(classroom);
            List<String> coursesIds = mapCourseIds(coursesList);
            List<ListCourseWorkResponse> courseWorkResponseList = 
                mapCourseWorksByCourse(classroom, coursesIds);
            return courseWorksList(classroom, courseWorkResponseList);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ArrayList<CourseWork>();
    }

    protected Classroom getClassroomInstance(String accessToken) throws Exception {
        return getClassroomService().instance(accessToken);
    }

    protected List<Course> searchCourses(Classroom service) throws IOException {
        return service.courses().list().execute().getCourses();
    }
    
    protected List<String> mapCourseIds(List<Course> listCourses) {
        return listCourses.stream().map(course -> course.getId()).collect(toList());
    }

    protected List<ListCourseWorkResponse> mapCourseWorksByCourse(Classroom service, List<String> courseIds) {
        return courseIds.stream().map(id -> searchCourseWorksByCourseId(service, id)).collect(toList());
    }

    protected ListCourseWorkResponse searchCourseWorksByCourseId(Classroom service, String id) {
        try {
            return service.courses().courseWork().list(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ListCourseWorkResponse();
    }
    
    protected List<CourseWork> courseWorksList(Classroom classroom, 
            List<ListCourseWorkResponse> response) {
        List<CourseWork> courseWorksList = response.stream()
            .map(courseWorkResponseList -> courseWorkResponseList.getCourseWork())
            .filter(courseWorks -> !isNull(courseWorks))
            .flatMap(courseWorks -> courseWorks.stream())
            .filter(courseWork -> isGreaterOrEqualThanNow(toLocalDate(courseWork.getDueDate())))
            .map(courseWork -> build(classroom, courseWork))
            .collect(toList());
        return courseWorksList;
    }

    protected CourseWork build(Classroom classroom,
            com.google.api.services.classroom.model.CourseWork courseWork) {
        return of(CourseWork::new)
            .with(CourseWork::setId, courseWork.getId())
            .with(CourseWork::setTitle, courseWork.getTitle())
            .with(CourseWork::setAlternateLink, courseWork.getAlternateLink())
            .with(CourseWork::setDueDate, courseWork.getDueDate())
            .with(CourseWork::setCourse, findCourseById(classroom, courseWork.getCourseId()))
            .build();
    }
    
    protected Course findCourseById(Classroom classroom, String id) {
        try {
            return classroom.courses().get(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Course();
    }
}
