package com.communify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourseDTO {

    private Long id;
    private StudentDTO student;
    private CourseDTO course;
}
