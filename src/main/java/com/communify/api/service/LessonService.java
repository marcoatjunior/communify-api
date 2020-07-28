package com.communify.api.service;

import static com.communify.api.helper.DateHelper.compare;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contracts.ILessonService;
import com.communify.api.contracts.IStudentService;
import com.communify.api.model.Lesson;
import com.communify.api.repository.LessonRepository;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class LessonService implements ILessonService {

    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Override
    public List<Lesson> list(String email) {
        return getLessonRepository().findByEmail(email).stream()
            .filter(lesson -> compare(lesson.getDeadline()))
            .collect(toList());
    }

    @Override
    public Lesson save(Lesson lesson) {
        return getLessonRepository().save(lesson);
    }

}
