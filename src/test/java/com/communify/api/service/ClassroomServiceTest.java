package com.communify.api.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.communify.api.CommunifyApplicationTests;
import com.google.api.services.classroom.Classroom;

import lombok.Getter;

@Getter
public class ClassroomServiceTest extends CommunifyApplicationTests {
    
    private static final String ACCESS_TOKEN = "accessToken";
    
    @InjectMocks
    private ClassroomService classroomService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldCreateNewInstanceByAccessToken() throws Exception {
        Classroom classroom = getClassroomService().instance(ACCESS_TOKEN);
        assertNotNull(classroom);
    }
    
}
