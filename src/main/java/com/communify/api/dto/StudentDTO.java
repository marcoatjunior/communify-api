package com.communify.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    private Long id;
    private String username;
    private String email;
    private List<CourseDTO> courses;
}
