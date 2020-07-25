package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Optional.ofNullable;

import com.communify.api.dto.UserDTO;
import com.communify.api.model.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDTO modelToDTO(User user) {
        return ofNullable(user).map(UserMapper::convertModelToDTO).orElse(null);
    }
    
    public static User dtoToModel(UserDTO userDTO) {
        return ofNullable(userDTO).map(UserMapper::convertDTOToModel).orElse(null);
    }

    private static UserDTO convertModelToDTO(User user) {
        return of(UserDTO::new)
            .with(UserDTO::setId, user.getId())
            .with(UserDTO::setName, user.getName())
            .with(UserDTO::setClassroomEmailAddress, user.getClassroomEmailAddress())
            .with(UserDTO::setMoodleEmailAddress, user.getMoodleEmailAddress())
            .with(UserDTO::setUrlPhoto, user.getUrlPhoto())
            .build();
    }
    
    private static User convertDTOToModel(UserDTO userDTO) {
        return of(User::new)
            .with(User::setName, userDTO.getName())
            .with(User::setClassroomEmailAddress, userDTO.getClassroomEmailAddress())
            .with(User::setMoodleEmailAddress, userDTO.getMoodleEmailAddress())
            .with(User::setUrlPhoto, userDTO.getUrlPhoto())
            .build();
    }
}
