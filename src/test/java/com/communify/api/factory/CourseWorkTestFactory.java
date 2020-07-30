package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;

import com.communify.api.model.CourseWork;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.Date;

public class CourseWorkTestFactory {

    public static final String ID = "id";
    public static final String TITLE = "Trabalho Teste";
    public static final String ALTERNATE_LINK = "https://trabalho.teste.br";
    public static final Date DUE_DATE = new Date();
    
    private CourseWorkTestFactory() {
    }
    
    public static CourseWork create() {
        return of(CourseWork::new)
            .with(CourseWork::setId, ID)
            .with(CourseWork::setTitle, TITLE)
            .with(CourseWork::setAlternateLink, ALTERNATE_LINK)
            .with(CourseWork::setDueDate, DUE_DATE)
            .with(CourseWork::setCourse, new Course())
            .build();
    }
    
    public static CourseWork createWithNoReturnDate() {
        return of(CourseWork::new)
            .with(CourseWork::setId, ID)
            .with(CourseWork::setTitle, TITLE)
            .with(CourseWork::setAlternateLink, ALTERNATE_LINK)
            .with(CourseWork::setCourse, new Course())
            .build();
    }
}
