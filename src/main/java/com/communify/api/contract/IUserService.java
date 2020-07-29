package com.communify.api.contract;

import com.communify.api.entity.User;

public interface IUserService {

    User findByClassroom(String email);
    User save(User user);
}
