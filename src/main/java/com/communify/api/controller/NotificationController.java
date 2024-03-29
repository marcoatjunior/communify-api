package com.communify.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.contract.INotificationService;

import lombok.Getter;

@RestController
@RequestMapping("/notifications")
@Getter
public class NotificationController {

    @Autowired
    private INotificationService notificationService;
    
    @CrossOrigin
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public void send(@RequestHeader("Authorization") String accessToken, 
            @RequestParam("email") String email) {
        getNotificationService().send(accessToken, email);
    }
}