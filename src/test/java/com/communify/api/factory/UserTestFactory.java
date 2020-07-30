package com.communify.api.factory;

import static com.communify.api.builder.GenericBuilder.of;

import com.communify.api.entity.User;

public class UserTestFactory {

    public static final Long ID = 1L;
    public static final String NAME = "Marco Taborda";
    public static final String CLASSROOM_EMAIL_ADDRESS = "marco@classroom.edu.br";
    public static final String MOODLE_EMAIL_ADDRESS = "marco@moodle.edu.br";
    public static final String URL_PHOTO = "https://foto.do.marco";
    
    private UserTestFactory() {
    }
    
    public static User create() {
        return of(User::new)
            .with(User::setId, ID)
            .with(User::setName, NAME)
            .with(User::setClassroomEmailAddress, CLASSROOM_EMAIL_ADDRESS)
            .with(User::setMoodleEmailAddress, MOODLE_EMAIL_ADDRESS)
            .with(User::setUrlPhoto, URL_PHOTO)
            .build();
      }
}
