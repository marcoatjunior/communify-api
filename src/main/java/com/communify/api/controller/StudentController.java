package com.communify.api.controller;

import static com.communify.api.mapper.StudentMapper.dtoToModel;
import static com.communify.api.mapper.StudentMapper.modelToDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.contract.IStudentService;
import com.communify.api.dto.StudentDTO;

import lombok.Getter;

@RestController
@RequestMapping("/students")
@Getter
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @CrossOrigin
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public List<StudentDTO> save(@RequestBody List<StudentDTO> list) {
        return list.stream()
            .map(studentDTO -> modelToDTO(getStudentService().save(dtoToModel(studentDTO))))
            .collect(Collectors.toList());
    }
}
