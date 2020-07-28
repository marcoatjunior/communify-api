package com.communify.api.contract;

import com.google.api.services.classroom.Classroom;

public interface IClassroomService {

    Classroom instance(String accessToken);
}
