package com.communify.api.controller;

import static com.communify.api.factory.CourseTestFactory.FULLNAME;
import static com.communify.api.factory.CourseTestFactory.ID;
import static com.communify.api.factory.CourseTestFactory.create;
import static com.communify.api.mapper.CourseMapper.modelsToDTOs;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.contract.ICourseService;
import com.communify.api.dto.CourseDTO;
import com.communify.api.entity.Course;
import com.communify.api.factory.LessonTestFactory;
import com.communify.api.factory.StudentTestFactory;
import com.communify.api.mapper.CourseMapper;

import lombok.Getter;

@Getter
public class CourseControllerTest extends CommunifyApplicationTests {

    @InjectMocks
    private CourseController courseController;
    
    @Mock
    private ICourseService courseService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSaveCoursesList() {
        Course course = create();
        when(getCourseService().save(any())).thenReturn(course);
        
        List<CourseDTO> list = getCourseController().save(
            modelsToDTOs(asList(course)));
        
        List<Course> coursesList = CourseMapper.dtosToModels(list);
        
        assertNotNull(coursesList);
        assertNotNull(course.getLessons());
        assertNotNull(course.getStudents());
        
        assertEquals(1, list.size());
        assertEquals(1, course.getLessons().size());
        assertEquals(1, course.getStudents().size());
        assertEquals(ID, course.getId());
        assertEquals(FULLNAME, course.getFullname());
        assertEquals(LessonTestFactory.create().getId(), course.getLessons().get(0).getId());
        assertEquals(StudentTestFactory.create().getId(), course.getStudents().get(0).getId());
    }
}
