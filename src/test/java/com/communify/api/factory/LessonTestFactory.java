package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.factory.DateHelperTestFactory.addDays;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.communify.api.entity.Course;
import com.communify.api.entity.Lesson;

public class LessonTestFactory {

    public static final Long ID = 1L;
    public static final String NAME = "marco.taborda";
    public static final Long DEADLINE = MILLISECONDS.toSeconds(
        addDays(2).getTimeInMillis());
    public static final String ACTIVITY_LINK = "https://google.com";
    public static final String COURSE_NAME = "Curso Teste";
    
    private LessonTestFactory() {
    }
    
    public static Lesson create() {
        return of(Lesson::new)
            .with(Lesson::setId, ID)
            .with(Lesson::setName, NAME)
            .with(Lesson::setDeadline, DEADLINE)
            .with(Lesson::setActivityLink, ACTIVITY_LINK)
            .with(Lesson::setCourse, of(Course::new)
                .with(Course::setFullname, COURSE_NAME).build())
            .build();
    }
}
