package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Arrays.asList;

import com.google.api.services.classroom.model.CourseWork;
import com.google.api.services.classroom.model.Date;
import com.google.api.services.classroom.model.ListCourseWorkResponse;

public class ClassroomListCourseWorksResponseTestFactory {

    public static final String ID = "Tarefa Classroom Id";
    public static final String COURSE_ID = "Curso Classroom Id";
    public static final String TITLE = "Tarefa Classroom";
    public static final String ALTERNATE_LINK = "Tarefa Classroom Link";
    public static final Date DUE_DATE = new Date();
    
    private ClassroomListCourseWorksResponseTestFactory() {
    }
    
    public static ListCourseWorkResponse create() throws Exception {
        return of(ListCourseWorkResponse::new).build()
            .setCourseWork(asList(of(CourseWork::new)
                .with(CourseWork::setId, ID)
                .with(CourseWork::setCourseId, COURSE_ID)
                .with(CourseWork::setTitle, TITLE)
                .with(CourseWork::setAlternateLink, ALTERNATE_LINK)
                .with(CourseWork::setDueDate, DUE_DATE)
                .build()));
    }
    
    public static ListCourseWorkResponse createWithNoReturnDate() throws Exception {
        return of(ListCourseWorkResponse::new).build()
            .setCourseWork(asList(of(CourseWork::new)
                .with(CourseWork::setId, ID)
                .with(CourseWork::setCourseId, COURSE_ID)
                .with(CourseWork::setTitle, TITLE)
                .with(CourseWork::setAlternateLink, ALTERNATE_LINK)
                .build()));
    }
}
