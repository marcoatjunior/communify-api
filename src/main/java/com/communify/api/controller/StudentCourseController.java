package com.communify.api.controller;

import static com.communify.api.mapper.StudentCourseMapper.dtoToModel;
import static com.communify.api.mapper.StudentCourseMapper.modelToDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.contracts.IStudentCourseService;
import com.communify.api.dto.StudentCourseDTO;

import lombok.Getter;

@RestController
@RequestMapping("/studentCourses")
@Getter
public class StudentCourseController {

    @Autowired
    private IStudentCourseService studentCourseService;

    @CrossOrigin
    @PostMapping(produces = "application/json")
    public StudentCourseDTO save(@RequestBody StudentCourseDTO studentCourseDTO) {
        return modelToDTO(getStudentCourseService().save(dtoToModel(studentCourseDTO)));
    }
}
