package com.communify.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contracts.ICourseService;
import com.communify.api.model.Course;
import com.communify.api.repository.CourseRepository;

import lombok.Getter;

@Service
@Getter
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    @Override
    public Course save(Course course) {
        return getCourseRepository().save(course);
    }
}
