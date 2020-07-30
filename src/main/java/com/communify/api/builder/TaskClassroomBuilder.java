package com.communify.api.builder;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.ClassroomDateHelper.toDate;
import static java.util.Optional.ofNullable;

import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.model.CourseWork;
import com.communify.api.model.Task;

public class TaskClassroomBuilder {
    
    private TaskClassroomBuilder() {
    }

    public static Task build(CourseWork courseWork) {
        return of(Task::new)
            .with(Task::setDescription, courseWork.getTitle())
            .with(Task::setCourse, ofNullable(courseWork.getCourse())
                .map(course -> course.getName()).orElse(null))
            .with(Task::setLink, courseWork.getAlternateLink())
            .with(Task::setReturnDate, toDate(courseWork.getDueDate()))
            .with(Task::setOrigin, TaskOriginEnum.Classroom)
            .build();
    }
}
