package com.communify.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.service.ITaskNotificationService;

import lombok.Getter;

@RestController
@RequestMapping("/notifications")
@Getter
public class TaskNotificationController {

    @Autowired
    private ITaskNotificationService notificationService;
    
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public void send(@RequestHeader("Authorization") String accessToken, 
            @RequestParam("email") String email) {
        getNotificationService().send(accessToken, email);
    }
}