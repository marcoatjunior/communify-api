package com.communify.api.controller;

import static com.communify.api.mapper.LessonMapper.dtoToModel;
import static com.communify.api.mapper.LessonMapper.modelToDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communify.api.contracts.ILessonService;
import com.communify.api.dto.LessonDTO;

import lombok.Getter;

@RestController
@RequestMapping("/lessons")
@Getter
public class LessonController {

    @Autowired
    private ILessonService lessonService;

    @CrossOrigin
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public List<LessonDTO> save(@RequestBody List<LessonDTO> list) {
        return list.stream()
            .map(lessonDTO -> modelToDTO(getLessonService().save(dtoToModel(lessonDTO))))
            .collect(Collectors.toList());
    }
}
