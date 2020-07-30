package com.communify.api.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.entity.Student;
import com.communify.api.factory.StudentTestFactory;
import com.communify.api.repository.StudentRepository;

import lombok.Getter;

@Getter
public class StudentServiceTest extends CommunifyApplicationTests {
    
    @InjectMocks
    private StudentService studentService;
    
    @Mock
    private StudentRepository studentRepository;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldFindStudentByEmail() {
        Student student = StudentTestFactory.create();
        when(getStudentRepository().findByEmail(any())).thenReturn(student);
        
        Student studentReturned = getStudentService().findByEmail(student.getEmail());
        
        assertEquals(student.getId(), studentReturned.getId());
        assertEquals(student.getUsername(), studentReturned.getUsername());
    }
    
    @Test
    public void shouldSaveNewStudent() {
        Student student = StudentTestFactory.create();
        when(getStudentRepository().save(any())).thenReturn(student);
        
        Student studentReturned = getStudentService().save(student);
        
        assertEquals(student.getId(), studentReturned.getId());
        assertEquals(student.getUsername(), studentReturned.getUsername());
    }
}
