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
import com.communify.api.entity.Course;
import com.communify.api.factory.CourseTestFactory;
import com.communify.api.repository.CourseRepository;

import lombok.Getter;

@Getter
public class CourseServiceTest extends CommunifyApplicationTests {
    
    @InjectMocks
    private CourseService courseService;
    
    @Mock
    private CourseRepository courseRepository;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSaveNewCourse() {
        Course course = CourseTestFactory.create();
        when(getCourseRepository().save(any())).thenReturn(course);
        
        Course courseReturned = getCourseService().save(course);
        
        assertEquals(course.getId(), courseReturned.getId());
        assertEquals(course.getFullname(), courseReturned.getFullname());
    }
}
