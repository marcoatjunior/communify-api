package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.StudentCourseDTO;
import com.communify.api.model.StudentCourse;

public class StudentCourseMapper {

    private StudentCourseMapper() {
    }
    
    public static List<StudentCourseDTO> modelsToDTOs(List<StudentCourse> studentCoursesList) {
        return ofNullable(studentCoursesList)
            .map(studentCourses -> studentCourses.stream()
                .map(StudentCourseMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static StudentCourseDTO modelToDTO(StudentCourse studentCourse) {
        return ofNullable(studentCourse).map(StudentCourseMapper::convertModelToDTO).orElse(null);
    }
    
    public static List<StudentCourse> dtosToModels(List<StudentCourseDTO> studentCoursesDTOList) {
        return ofNullable(studentCoursesDTOList)
            .map(studentCoursesDTO -> studentCoursesDTO.stream()
                .map(StudentCourseMapper::dtoToModel)
                .collect(toList())).orElse(emptyList());
    }
    
    public static StudentCourse dtoToModel(StudentCourseDTO studentCourseDTO) {
        return ofNullable(studentCourseDTO).map(StudentCourseMapper::convertDTOToModel).orElse(null);
    }

    private static StudentCourseDTO convertModelToDTO(StudentCourse studentCourse) {
        return of(StudentCourseDTO::new)
            .with(StudentCourseDTO::setId, studentCourse.getId())
            .with(StudentCourseDTO::setStudent, StudentMapper.modelToDTO(studentCourse.getStudent()))
            .with(StudentCourseDTO::setCourse, CourseMapper.modelToDTO(studentCourse.getCourse()))
            .build();
    }
    
    private static StudentCourse convertDTOToModel(StudentCourseDTO studentCourseDTO) {
        return of(StudentCourse::new)
            .with(StudentCourse::setId, studentCourseDTO.getId())
            .with(StudentCourse::setStudent, StudentMapper.dtoToModel(studentCourseDTO.getStudent()))
            .with(StudentCourse::setCourse, CourseMapper.dtoToModel(studentCourseDTO.getCourse()))
            .build();
    }
}
