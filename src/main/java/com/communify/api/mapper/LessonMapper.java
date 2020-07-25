package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.CourseWorkDTO;
import com.communify.api.enumerator.CourseWorkOriginEnum;
import com.communify.api.model.Lesson;

public class LessonMapper {

    private LessonMapper() {
    }
    
    public static List<CourseWorkDTO> modelsToDTOs(List<Lesson> listLessons) {
        return ofNullable(listLessons)
            .map(lessons -> lessons.stream()
                .map(LessonMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static CourseWorkDTO modelToDTO(Lesson lesson) {
        return ofNullable(lesson).map(LessonMapper::convertModelToDTO).orElse(null);
    }

    private static CourseWorkDTO convertModelToDTO(Lesson lesson) {
        return of(CourseWorkDTO::new)
            .with(CourseWorkDTO::setId, lesson.getId().toString())
            .with(CourseWorkDTO::setDescription, lesson.getName())
            .with(CourseWorkDTO::setLink, lesson.getActivityLink())
            .with(CourseWorkDTO::setReturnDate, null)
            .with(CourseWorkDTO::setOrigin, CourseWorkOriginEnum.Moodle)
            .build();
    }
    
}
