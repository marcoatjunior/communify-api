package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;

import java.util.Date;

import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.model.Task;

public class TaskTestFactory {

    public static final String ID = "id";
    public static final String DESCRIPTION = "Marco Taborda";
    public static final String COURSE = "marco@classroom.edu.br";
    public static final String LINK = "marco@moodle.edu.br";
    public static final Date RETURN_DATE = new Date();
    
    private TaskTestFactory() {
    }
    
    public static Task create() {
        return of(Task::new)
            .with(Task::setId, ID)
            .with(Task::setDescription, DESCRIPTION)
            .with(Task::setCourse, COURSE)
            .with(Task::setLink, LINK)
            .with(Task::setReturnDate, RETURN_DATE)
            .with(Task::setOrigin, TaskOriginEnum.Classroom)
            .build();
      }
}
