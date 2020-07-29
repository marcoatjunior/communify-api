package com.communify.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communify.api.contract.IStudentService;
import com.communify.api.entity.Student;
import com.communify.api.repository.StudentRepository;

import lombok.Getter;

@Service
@Getter
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public Student findByEmail(String email) {
        return getStudentRepository().findByEmail(email);
    }
    
    @Override
    public Student save(Student student) {
        return getStudentRepository().save(student);
    }
}
