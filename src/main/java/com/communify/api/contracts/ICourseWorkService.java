package com.communify.api.contracts;

import java.util.List;

import com.communify.api.model.CourseWork;

public interface ICourseWorkService {

    List<CourseWork> list(String accessToken);
}
