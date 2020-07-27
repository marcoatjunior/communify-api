package com.communify.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {

    private Long id;
    private String fullname;
    private List<StudentCourseDTO> studentCourses;
    private List<LessonDTO> lessons;
}
