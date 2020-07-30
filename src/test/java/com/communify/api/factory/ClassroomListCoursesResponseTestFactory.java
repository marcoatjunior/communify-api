package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Arrays.asList;

import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.ListCoursesResponse;

public class ClassroomListCoursesResponseTestFactory {

    public static final String COURSE_ID = "Curso Classroom Id";
    public static final String COURSE_NAME = "Curso Classroom Teste";
    
    private ClassroomListCoursesResponseTestFactory() {
    }
    
    public static ListCoursesResponse create() throws Exception {
        return of(ListCoursesResponse::new).build()
            .setCourses(asList(of(Course::new)
                .with(Course::setId, COURSE_ID)
                .with(Course::setName, COURSE_NAME)
                .build()));
      }
}
