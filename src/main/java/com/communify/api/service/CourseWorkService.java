package com.communify.api.service;

import java.util.List;

import com.google.api.services.classroom.model.CourseWork;

public interface CourseWorkService {

    List<CourseWork> list(String accessToken);
}
