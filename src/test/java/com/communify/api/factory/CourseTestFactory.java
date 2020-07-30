package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Arrays.asList;

import com.communify.api.entity.Course;

public class CourseTestFactory {

    public static final Long ID = 1L;
    public static final String FULLNAME = "Trabalho de Conclusão de Curso II";
    
    private CourseTestFactory () {
    }
    
    public static Course create() {
        return of(Course::new)
            .with(Course::setId, ID)
            .with(Course::setFullname, FULLNAME)
            .with(Course::setLessons, asList(LessonTestFactory.create()))
            .with(Course::setStudents, asList(StudentTestFactory.create()))
            .build();
      }
}
