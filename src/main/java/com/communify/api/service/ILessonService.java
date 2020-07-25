package com.communify.api.service;

import java.util.List;

import com.communify.api.model.Lesson;

public interface ILessonService {

    List<Lesson> list(String email);
}
