package com.communify.api.controller;

import static com.communify.api.mapper.StudentMapper.dtoToModel;
import static com.communify.api.mapper.StudentMapper.modelToDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.contracts.IStudentService;
import com.communify.api.dto.StudentDTO;

import lombok.Getter;

@RestController
@RequestMapping("/students")
@Getter
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @CrossOrigin
    @PostMapping(produces = "application/json")
    public StudentDTO save(@RequestBody StudentDTO studentDTO) {
        return modelToDTO(getStudentService().save(dtoToModel(studentDTO)));
    }
}
