package com.communify.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contracts.IStudentCourseService;
import com.communify.api.model.StudentCourse;
import com.communify.api.repository.StudentCourseRepository;

import lombok.Getter;

@Service
@Getter
public class StudentCourseService implements IStudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;
    
    @Override
    public StudentCourse save(StudentCourse studentCourse) {
        return getStudentCourseRepository().save(studentCourse);
    }
}
