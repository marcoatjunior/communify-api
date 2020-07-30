package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Arrays.asList;

import com.communify.api.entity.Course;
import com.communify.api.entity.Student;

public class StudentTestFactory {

    public static final Long ID = 1L;
    public static final String USERNAME = "marco.taborda";
    public static final String EMAIL = "marcotaborda.jr@gmail.com";
    public static final String COURSE_NAME = "Curso Teste";
    
    private StudentTestFactory() {
    }
    
    public static Student create() {
        return of(Student::new)
            .with(Student::setId, ID)
            .with(Student::setUsername, USERNAME)
            .with(Student::setEmail, EMAIL)
            .with(Student::setCourses, null)
            .with(Student::setCourses, asList(of(Course::new)
                .with(Course::setFullname, COURSE_NAME).build()))
            .build();
      }
}
