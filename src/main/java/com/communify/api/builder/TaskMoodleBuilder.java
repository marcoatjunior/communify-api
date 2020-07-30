package com.communify.api.builder;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.MoodleDateHelper.toDate;
import static java.util.Optional.ofNullable;

import com.communify.api.entity.Lesson;
import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.model.Task;

public class TaskMoodleBuilder {
    
    private TaskMoodleBuilder() {
    }

    public static Task build(Lesson lesson) {
        return of(Task::new)
            .with(Task::setDescription, lesson.getName())
            .with(Task::setCourse, ofNullable(lesson.getCourse())
                .map(course -> course.getFullname()).orElse(null))
            .with(Task::setLink, lesson.getActivityLink())
            .with(Task::setReturnDate, toDate(lesson.getDeadline()))
            .with(Task::setOrigin, TaskOriginEnum.Moodle)
            .build();
    }
}
