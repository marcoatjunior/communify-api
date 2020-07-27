package com.communify.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.service.ITermService;

import lombok.Getter;

@RestController
@RequestMapping("/terms")
@Getter
public class TermController {

    @Autowired
    private ITermService termService;
    
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public void send(@RequestParam("email") String email, HttpServletRequest request) {
        getTermService().send(email, request.getRemoteAddr());
    }
}