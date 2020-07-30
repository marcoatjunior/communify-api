package com.communify.api.service;

import static com.communify.api.helper.DateHelper.isGreaterOrEqualThanNow;
import static com.communify.api.helper.MoodleDateHelper.toLocalDate;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contract.ILessonService;
import com.communify.api.entity.Lesson;
import com.communify.api.repository.LessonRepository;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class LessonService implements ILessonService {

    @Autowired
    private LessonRepository lessonRepository;
    
    @Override
    public List<Lesson> list(String email) {
        return getLessonRepository().findByEmail(email).stream()
            .filter(lesson -> isNull(lesson.getDeadline()) || 
                isGreaterOrEqualThanNow(toLocalDate(lesson.getDeadline())))
            .collect(toList());
    }

    @Override
    public Lesson save(Lesson lesson) {
        return getLessonRepository().save(lesson);
    }

}
