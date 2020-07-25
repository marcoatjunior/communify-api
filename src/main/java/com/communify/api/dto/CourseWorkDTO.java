package com.communify.api.dto;

import com.communify.api.enumerator.CourseWorkOriginEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseWorkDTO {

    private String id;
    private String description;
    private String link;
    private String returnDate;
    private CourseWorkOriginEnum origin;
}
