package com.communify.api.model;

import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseWork {

    private String id;
    private String title;
    private String alternateLink;
    private Date dueDate;
    private Course course;
}
