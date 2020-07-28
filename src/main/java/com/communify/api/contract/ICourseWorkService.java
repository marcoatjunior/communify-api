package com.communify.api.contract;

import java.util.List;

import com.communify.api.model.CourseWork;

public interface ICourseWorkService {

    List<CourseWork> list(String accessToken);
}
