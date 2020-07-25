package com.communify.api.controller;

import static com.communify.api.mapper.CourseWorkMapper.modelsToDTOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.dto.CourseWorkDTO;
import com.communify.api.service.ICourseWorkService;

import lombok.Getter;

@RestController
@RequestMapping("/courseWorks")
@Getter
public class CourseWorkController {

    @Autowired
    private ICourseWorkService courseWorkService;
    
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public List<CourseWorkDTO> list(@RequestHeader("Authorization") String accessToken) {
        return modelsToDTOs(getCourseWorkService().list(accessToken));
    }
    
}
