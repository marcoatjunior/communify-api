package com.communify.api.service;

import com.google.api.services.classroom.Classroom;

public interface IClassroomService {

    Classroom instance(String accessToken);
}
