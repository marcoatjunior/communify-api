package com.communify.api.contracts;

import com.google.api.services.classroom.Classroom;

public interface IClassroomService {

    Classroom instance(String accessToken);
}
