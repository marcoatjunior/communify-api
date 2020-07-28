package com.communify.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contract.IUserService;
import com.communify.api.model.User;
import com.communify.api.repository.UserRepository;

import lombok.Getter;

@Service
@Getter
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User findByClassroom(String email) {
        return getUserRepository().findByClassroom(email);
    }
    
    @Override
    public User save(User user) {
        return getUserRepository().save(user);
    }
}
