package com.communify.api.contract;

import com.communify.api.model.Student;

public interface IStudentService {

    Student findByEmail(String email);
    Student save(Student student);
}
