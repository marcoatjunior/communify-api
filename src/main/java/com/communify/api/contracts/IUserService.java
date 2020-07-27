package com.communify.api.contracts;

import com.communify.api.model.User;

public interface IUserService {

    User findByClassroom(String email);
    User save(User user);
}
