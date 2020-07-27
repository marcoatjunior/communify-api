package com.communify.api.contracts;

import com.communify.api.model.Student;

public interface IStudentService {

    Student findByEmail(String email);
    Student save(Student student);
}
