package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.LessonDTO;
import com.communify.api.model.Lesson;

public class LessonMapper {

    private LessonMapper() {
    }
    
    public static List<LessonDTO> modelsToDTOs(List<Lesson> lessonsList) {
        return ofNullable(lessonsList)
            .map(lessons -> lessons.stream()
                .map(LessonMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static LessonDTO modelToDTO(Lesson lesson) {
        return ofNullable(lesson).map(LessonMapper::convertModelToDTO).orElse(null);
    }
    
    public static List<Lesson> dtosToModels(List<LessonDTO> lessonsDTOList) {
        return ofNullable(lessonsDTOList)
            .map(lessonsDTO -> lessonsDTO.stream()
                .map(LessonMapper::dtoToModel)
                .collect(toList())).orElse(emptyList());
    }
    
    public static Lesson dtoToModel(LessonDTO lessonDTO) {
        return ofNullable(lessonDTO).map(LessonMapper::convertDTOToModel).orElse(null);
    }

    private static LessonDTO convertModelToDTO(Lesson lesson) {
        return of(LessonDTO::new)
            .with(LessonDTO::setId, lesson.getId())
            .with(LessonDTO::setName, lesson.getName())
            .with(LessonDTO::setDeadline, lesson.getDeadline())
            .with(LessonDTO::setActivityLink, lesson.getActivityLink())
            .with(LessonDTO::setCourse, CourseMapper.modelToDTO(lesson.getCourse()))
            .build();
    }
    
    private static Lesson convertDTOToModel(LessonDTO lessonDTO) {
        return of(Lesson::new)
            .with(Lesson::setId, lessonDTO.getId())
            .with(Lesson::setName, lessonDTO.getName())
            .with(Lesson::setDeadline, lessonDTO.getDeadline())
            .with(Lesson::setActivityLink, lessonDTO.getActivityLink())
            .with(Lesson::setCourse, CourseMapper.dtoToModel(lessonDTO.getCourse()))
            .build();
    }
}
