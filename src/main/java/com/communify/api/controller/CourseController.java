package com.communify.api.controller;

import static com.communify.api.mapper.CourseMapper.dtoToModel;
import static com.communify.api.mapper.CourseMapper.modelToDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public List<CourseDTO> save(@RequestBody List<CourseDTO> list) {
        return list.stream()
            .map(course -> modelToDTO(getCourseService().save(dtoToModel(course))))
            .collect(Collectors.toList());
    }
}
