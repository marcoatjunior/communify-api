package com.communify.api.controller;

import static com.communify.api.mapper.UserMapper.modelToDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.dto.UserDTO;
import com.communify.api.service.IUserService;

import lombok.Getter;

@RestController
@RequestMapping("/users")
@Getter
public class UserController {

    @Autowired
    private IUserService userService;
    
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public UserDTO findByClassroom(@RequestParam("email") String email) {
        return modelToDTO(getUserService().findByClassroom(email));
    }

    @CrossOrigin
    @PostMapping(produces = "application/json")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return modelToDTO(getUserService().save(userDTO));
    }
}
