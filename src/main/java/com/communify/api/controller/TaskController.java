package com.communify.api.controller;

import static com.communify.api.mapper.CourseWorkMapper.modelsToDTOs;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.dto.TaskDTO;
import com.communify.api.mapper.LessonMapper;
import com.communify.api.service.ICourseWorkService;
import com.communify.api.service.ILessonService;

import lombok.Getter;

@RestController
@RequestMapping("/tasks")
@Getter
public class TaskController {

    @Autowired
    private ICourseWorkService courseWorkService;
    
    @Autowired
    private ILessonService lessonService;
    
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public List<TaskDTO> list(@RequestHeader("Authorization") String accessToken, 
            @RequestParam("email") String email) {
        List<TaskDTO> classroomDTOs = modelsToDTOs(getCourseWorkService().list(accessToken));
        List<TaskDTO> moodleDTOs = LessonMapper.modelsToDTOs(getLessonService().list(email));
        return concat(classroomDTOs.stream(), moodleDTOs.stream())
                .sorted(comparing(TaskDTO::getReturnDate).reversed()).collect(toList());
    }
    
}
