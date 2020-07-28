package com.communify.api.contract;

import java.util.List;

import com.communify.api.model.Lesson;

public interface ILessonService {

    List<Lesson> list(String email);
    Lesson save(Lesson lesson);
}
