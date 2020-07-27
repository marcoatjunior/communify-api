package com.communify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDTO {

    private Long id;
    private String name;
    private Long deadline;
    private String activityLink;
    private CourseDTO course;
}
