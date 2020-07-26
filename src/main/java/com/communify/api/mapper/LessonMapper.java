package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.DateHelper.transform;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.TaskDTO;
import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.model.Lesson;

public class LessonMapper {

    private LessonMapper() {
    }
    
    public static List<TaskDTO> modelsToDTOs(List<Lesson> listLessons) {
        return ofNullable(listLessons)
            .map(lessons -> lessons.stream()
                .map(LessonMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static TaskDTO modelToDTO(Lesson lesson) {
        return ofNullable(lesson).map(LessonMapper::convertModelToDTO).orElse(null);
    }

    private static TaskDTO convertModelToDTO(Lesson lesson) {
        return of(TaskDTO::new)
            .with(TaskDTO::setId, lesson.getId().toString())
            .with(TaskDTO::setDescription, lesson.getName())
            .with(TaskDTO::setLink, lesson.getActivityLink())
            .with(TaskDTO::setReturnDate, transform(lesson.getDeadline()))
            .with(TaskDTO::setOrigin, TaskOriginEnum.Moodle)
            .build();
    }
    
}
