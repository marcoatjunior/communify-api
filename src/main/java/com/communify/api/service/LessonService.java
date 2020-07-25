package com.communify.api.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.model.Lesson;
import com.communify.api.model.Student;
import com.communify.api.repository.StudentRepository;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class LessonService implements ILessonService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public List<Lesson> list(String email) {
        Iterable<Student> listAaa = getStudentRepository().findAll();
        Student student = getStudentRepository().findByEmail(email);
        return student.getStudentCourses().stream()
            .map(userCourse -> userCourse.getCourse())
            .flatMap(course -> course.getLessons().stream())
            .collect(toList());
    }

}
