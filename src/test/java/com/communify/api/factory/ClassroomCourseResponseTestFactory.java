package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;

import com.google.api.services.classroom.model.Course;

public class ClassroomCourseResponseTestFactory {

    public static final String COURSE_ID = "Curso Classroom Id";
    public static final String COURSE_NAME = "Curso Classroom Teste";
    
    private ClassroomCourseResponseTestFactory() {
    }
    
    public static Course create() throws Exception {
        return of(Course::new).build()
            .setId(COURSE_ID)
            .setName(COURSE_NAME);
      }
}
