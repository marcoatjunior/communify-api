package com.communify.api.builder;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.MoodleDateHelper.toDate;

import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.model.Lesson;
import com.communify.api.model.Task;

public class TaskMoodleBuilder {

    public static Task build(Lesson lesson) {
        return of(Task::new)
            .with(Task::setDescription, lesson.getName())
            .with(Task::setCourse, lesson.getCourse().getFullname())
            .with(Task::setLink, lesson.getActivityLink())
            .with(Task::setReturnDate, toDate(lesson.getDeadline()))
            .with(Task::setOrigin, TaskOriginEnum.Moodle)
            .build();
    }
}
