package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Optional.ofNullable;

import com.communify.api.dto.CourseDTO;
import com.communify.api.model.Course;

public class CourseMapper {

    private CourseMapper() {
    }

    public static CourseDTO modelToDTO(Course course) {
        return ofNullable(course).map(CourseMapper::convertModelToDTO).orElse(null);
    }
    
    public static Course dtoToModel(CourseDTO courseDTO) {
        return ofNullable(courseDTO).map(CourseMapper::convertDTOToModel).orElse(null);
    }

    private static CourseDTO convertModelToDTO(Course course) {
        return of(CourseDTO::new)
            .with(CourseDTO::setId, course.getId())
            .with(CourseDTO::setFullname, course.getFullname())
            .with(CourseDTO::setStudentCourses, 
                StudentCourseMapper.modelsToDTOs(course.getStudentCourses()))
            .with(CourseDTO::setLessons, LessonMapper.modelsToDTOs(course.getLessons()))
            .build();
    }
    
    private static Course convertDTOToModel(CourseDTO courseDTO) {
        return of(Course::new)
            .with(Course::setId, courseDTO.getId())
            .with(Course::setFullname, courseDTO.getFullname())
            .with(Course::setStudentCourses, 
                StudentCourseMapper.dtosToModels(courseDTO.getStudentCourses()))
            .with(Course::setLessons, LessonMapper.dtosToModels(courseDTO.getLessons()))
            .build();
    }
}
