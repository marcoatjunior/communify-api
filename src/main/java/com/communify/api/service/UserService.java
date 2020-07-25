package com.communify.api.service;

import com.communify.api.dto.UserDTO;
import com.communify.api.model.User;

public interface UserService {

    User findByClassroom(String email);
    User save(UserDTO userDTO);
}
