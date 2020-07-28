package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.CourseDTO;
import com.communify.api.model.Course;

public class CourseMapper {

    private CourseMapper() {
    }
    
    public static List<CourseDTO> modelsToDTOs(List<Course> coursesList) {
        return ofNullable(coursesList)
            .map(courses -> courses.stream()
                .map(CourseMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static CourseDTO modelToDTO(Course course) {
        return ofNullable(course).map(CourseMapper::convertModelToDTO).orElse(null);
    }
    
    public static List<Course> dtosToModels(List<CourseDTO> coursesDTOList) {
        return ofNullable(coursesDTOList)
            .map(coursesDTO -> coursesDTO.stream()
                .map(CourseMapper::dtoToModel)
                .collect(toList())).orElse(emptyList());
    }
    
    public static Course dtoToModel(CourseDTO courseDTO) {
        return ofNullable(courseDTO).map(CourseMapper::convertDTOToModel).orElse(null);
    }

    private static CourseDTO convertModelToDTO(Course course) {
        return of(CourseDTO::new)
            .with(CourseDTO::setId, course.getId())
            .with(CourseDTO::setFullname, course.getFullname())
            .with(CourseDTO::setStudents, 
                ofNullable(course.getStudents())
                    .map(students -> StudentMapper.
                        modelsToDTOs(students)).orElse(null))
            .with(CourseDTO::setLessons, ofNullable(course.getLessons())
                    .map(lessons -> LessonMapper.
                        modelsToDTOs(lessons)).orElse(null))
            .build();
    }
    
    private static Course convertDTOToModel(CourseDTO courseDTO) {
        return of(Course::new)
            .with(Course::setFullname, courseDTO.getFullname())
            .with(Course::setStudents, 
                ofNullable(courseDTO.getStudents())
                    .map(students -> StudentMapper.
                        dtosToModels(students)).orElse(null))
            .with(Course::setLessons, ofNullable(courseDTO.getLessons())
                    .map(lessons -> LessonMapper.
                        dtosToModels(lessons)).orElse(null))
            .build();
    }
}
