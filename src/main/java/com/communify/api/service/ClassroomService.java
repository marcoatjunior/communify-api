package com.communify.api.service;

import com.google.api.services.classroom.Classroom;

public interface ClassroomService {

    Classroom instance(String accessToken);
}
