package com.communify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String classroomEmailAddress;
    private String moodleEmailAddress;
}
