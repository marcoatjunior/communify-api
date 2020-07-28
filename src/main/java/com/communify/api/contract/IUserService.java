package com.communify.api.contract;

import com.communify.api.model.User;

public interface IUserService {

    User findByClassroom(String email);
    User save(User user);
}
