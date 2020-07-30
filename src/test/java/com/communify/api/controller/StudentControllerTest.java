package com.communify.api.controller;

import static com.communify.api.factory.StudentTestFactory.EMAIL;
import static com.communify.api.factory.StudentTestFactory.ID;
import static com.communify.api.factory.StudentTestFactory.USERNAME;
import static com.communify.api.factory.StudentTestFactory.create;
import static com.communify.api.mapper.StudentMapper.modelToDTO;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.contract.IStudentService;
import com.communify.api.dto.StudentDTO;
import com.communify.api.entity.Student;

import lombok.Getter;

@Getter
public class StudentControllerTest extends CommunifyApplicationTests {

    @InjectMocks
    private StudentController studentController;
    
    @Mock
    private IStudentService studentService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSaveStudentsList() {
        Student student = create();
        when(getStudentService().save(any())).thenReturn(student);
        
        List<StudentDTO> list = getStudentController().save(
            asList(modelToDTO(student)));
        
        assertEquals(1, list.size());
        assertEquals(ID, student.getId());
        assertEquals(EMAIL, student.getEmail());
        assertEquals(USERNAME, student.getUsername());
    }
}
