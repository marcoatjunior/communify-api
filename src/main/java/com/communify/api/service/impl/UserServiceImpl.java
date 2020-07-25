package com.communify.api.service.impl;

import static com.communify.api.mapper.UserMapper.dtoToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.dto.UserDTO;
import com.communify.api.model.User;
import com.communify.api.repository.UserRepository;
import com.communify.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public User findByClassroom(String email) {
        return userRepository.findByClassroom(email);
    }
    
    @Override
    public User save(UserDTO userDTO) {
        return userRepository.save(dtoToModel(userDTO));
    }
}
