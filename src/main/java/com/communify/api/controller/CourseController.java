package com.communify.api.controller;

import static com.communify.api.mapper.CourseMapper.dtoToModel;
import static com.communify.api.mapper.CourseMapper.modelToDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.contracts.ICourseService;
import com.communify.api.dto.CourseDTO;

import lombok.Getter;

@RestController
@RequestMapping("/courses")
@Getter
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @CrossOrigin
    @PostMapping(produces = "application/json")
    public CourseDTO save(@RequestBody CourseDTO courseDTO) {
        return modelToDTO(getCourseService().save(dtoToModel(courseDTO)));
    }
}
