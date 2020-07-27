package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Optional.ofNullable;

import com.communify.api.dto.StudentDTO;
import com.communify.api.model.Student;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDTO modelToDTO(Student student) {
        return ofNullable(student).map(StudentMapper::convertModelToDTO).orElse(null);
    }
    
    public static Student dtoToModel(StudentDTO studentDTO) {
        return ofNullable(studentDTO).map(StudentMapper::convertDTOToModel).orElse(null);
    }

    private static StudentDTO convertModelToDTO(Student student) {
        return of(StudentDTO::new)
            .with(StudentDTO::setId, student.getId())
            .with(StudentDTO::setUsername, student.getUsername())
            .with(StudentDTO::setEmail, student.getEmail())
            .with(StudentDTO::setStudentCourses, 
                StudentCourseMapper.modelsToDTOs(student.getStudentCourses()))
            .build();
    }
    
    private static Student convertDTOToModel(StudentDTO studentDTO) {
        return of(Student::new)
            .with(Student::setId, studentDTO.getId())
            .with(Student::setUsername, studentDTO.getUsername())
            .with(Student::setEmail, studentDTO.getEmail())
            .with(Student::setStudentCourses, 
                StudentCourseMapper.dtosToModels(studentDTO.getStudentCourses()))
            .build();
    }
}
