package com.communify.api.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.dto.CourseWorkDTO;
import com.communify.api.mapper.CourseWorkMapper;
import com.communify.api.mapper.LessonMapper;
import com.communify.api.service.ICourseWorkService;
import com.communify.api.service.ILessonService;

import lombok.Getter;

@RestController
@RequestMapping("/courseWorks")
@Getter
public class CourseWorkController {

    @Autowired
    private ICourseWorkService courseWorkService;
    
    @Autowired
    private ILessonService lessonService;
    
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public List<CourseWorkDTO> list(@RequestHeader("Authorization") String accessToken, 
            @RequestParam("email") String email) {
        List<CourseWorkDTO> courseWorkDTOsClassroom = CourseWorkMapper.
                modelsToDTOs(getCourseWorkService().list(accessToken));
        List<CourseWorkDTO> courseWorkDTOsMoodle = LessonMapper.
                modelsToDTOs(getLessonService().list(email));
        return Stream.concat(courseWorkDTOsClassroom.stream(), 
                courseWorkDTOsMoodle.stream()).collect(toList());
    }
    
}
