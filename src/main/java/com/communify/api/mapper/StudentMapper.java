package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.StudentDTO;
import com.communify.api.entity.Student;

public class StudentMapper {

    private StudentMapper() {
    }
    
    public static List<StudentDTO> modelsToDTOs(List<Student> studentsList) {
        return ofNullable(studentsList)
            .map(students -> students.stream()
                .map(StudentMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static StudentDTO modelToDTO(Student student) {
        return ofNullable(student).map(StudentMapper::convertModelToDTO).orElse(null);
    }
    
    public static List<Student> dtosToModels(List<StudentDTO> studentsDTOList) {
        return ofNullable(studentsDTOList)
            .map(studentsDTO -> studentsDTO.stream()
                .map(StudentMapper::dtoToModel)
                .collect(toList())).orElse(emptyList());
    }
    
    public static Student dtoToModel(StudentDTO studentDTO) {
        return ofNullable(studentDTO).map(StudentMapper::convertDTOToModel).orElse(null);
    }

    private static StudentDTO convertModelToDTO(Student student) {
        return of(StudentDTO::new)
            .with(StudentDTO::setId, student.getId())
            .with(StudentDTO::setUsername, student.getUsername())
            .with(StudentDTO::setEmail, student.getEmail())
            .with(StudentDTO::setCourses, 
                ofNullable(student.getCourses())
                    .map(courses -> CourseMapper.
                        modelsToDTOs(courses)).orElse(null))
            .build();
    }
    
    private static Student convertDTOToModel(StudentDTO studentDTO) {
        return of(Student::new)
            .with(Student::setId, studentDTO.getId())
            .with(Student::setUsername, studentDTO.getUsername())
            .with(Student::setEmail, studentDTO.getEmail())
            .with(Student::setCourses,
                ofNullable(studentDTO.getCourses())
                    .map(courses -> CourseMapper.
                        dtosToModels(courses)).orElse(null))
            .build();
    }
}
