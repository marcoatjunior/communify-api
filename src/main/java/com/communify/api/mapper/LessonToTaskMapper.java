package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.MoodleDateHelper.toDate;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.TaskDTO;
import com.communify.api.entity.Lesson;
import com.communify.api.enumerator.TaskOriginEnum;

public class LessonToTaskMapper {

    private LessonToTaskMapper() {
    }
    
    public static List<TaskDTO> modelsToDTOs(List<Lesson> listLessons) {
        return ofNullable(listLessons)
            .map(lessons -> lessons.stream()
                .map(LessonToTaskMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static TaskDTO modelToDTO(Lesson lesson) {
        return ofNullable(lesson).map(LessonToTaskMapper::convertModelToDTO).orElse(null);
    }

    private static TaskDTO convertModelToDTO(Lesson lesson) {
        return of(TaskDTO::new)
            .with(TaskDTO::setId, lesson.getId().toString())
            .with(TaskDTO::setDescription, lesson.getName())
            .with(TaskDTO::setCourse, ofNullable(lesson.getCourse())
                .map(course -> course.getFullname()).orElse(null))
            .with(TaskDTO::setLink, lesson.getActivityLink())
            .with(TaskDTO::setReturnDate, toDate(lesson.getDeadline()))
            .with(TaskDTO::setOrigin, TaskOriginEnum.Moodle)
            .build();
    }
    
}
