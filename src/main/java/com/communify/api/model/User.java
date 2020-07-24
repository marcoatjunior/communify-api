package com.communify.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String classroomEmailAddress;
    private String moodleEmailAddress;
}
