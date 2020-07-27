package com.communify.api.contracts;

import java.util.List;

import com.google.api.services.classroom.model.CourseWork;

public interface ICourseWorkService {

    List<CourseWork> list(String accessToken);
}
