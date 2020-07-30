package com.communify.api.service;

import static org.mockito.Mockito.when;
import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.factory.ClassroomListCourseWorksResponseTestFactory.create;
import static com.communify.api.factory.ClassroomListCourseWorksResponseTestFactory.createWithNoReturnDate;
import static java.util.Arrays.asList;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.factory.ClassroomCourseResponseTestFactory;
import com.communify.api.factory.ClassroomListCoursesResponseTestFactory;
import com.communify.api.factory.ClassroomTestFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.Classroom.Courses.CourseWork.List;
import com.google.api.services.classroom.Classroom.Courses.Get;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.ListCourseWorkResponse;
import com.google.api.services.classroom.Classroom.Courses;
import com.google.api.services.classroom.Classroom.Courses.CourseWork;

import lombok.Getter;

@Getter
public class CourseWorkServiceTest extends CommunifyApplicationTests {
    
    private static final String ACCESS_TOKEN = "accessToken";
    
    @InjectMocks
    private CourseWorkService courseWorkService;
    
    @Mock
    private ClassroomService classroomService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldListCourseWorksEmpty() throws Exception {
        Classroom classroom = ClassroomTestFactory.create();
        when(getClassroomService().instance(any())).thenReturn(classroom);
        
        java.util.List<com.communify.api.model.CourseWork> courseWorksList = 
            getCourseWorkService().list(ACCESS_TOKEN);
        assertNotNull(courseWorksList);
        assertTrue(isEmpty(courseWorksList));
    }
    
    @Test
    public void shouldListCourseWorks() throws Exception {
        Classroom classroom = mockClassroomService(true);
        when(getClassroomService().instance(any())).thenReturn(classroom);
        
        java.util.List<com.communify.api.model.CourseWork> courseWorksList = 
            getCourseWorkService().list(ACCESS_TOKEN);
        assertNotNull(courseWorksList);
        assertFalse(isEmpty(courseWorksList));
    }
    
    @Test
    public void shouldListCourseWorksEvenIfReturnDateIsNull() throws Exception {
        Classroom classroom = mockClassroomService(false);
        when(getClassroomService().instance(any())).thenReturn(classroom);
        
        java.util.List<com.communify.api.model.CourseWork> courseWorksList = 
            getCourseWorkService().list(ACCESS_TOKEN);
        assertNotNull(courseWorksList);
        assertFalse(isEmpty(courseWorksList));
    }
    
    @Test
    public void shouldReturnNewCourseWhenServiceIsUnauthorized() throws Exception {
        Classroom classroom = ClassroomTestFactory.create();
        when(getClassroomService().instance(any())).thenReturn(classroom);
        
        Course course = getCourseWorkService().findCourseById(classroom, "1");
        assertNotNull(course);
    }
    
    @Test
    public void shouldReturnNewCourseWorkResponseWhenServiceIsUnauthorized() throws Exception {
        Classroom classroom = ClassroomTestFactory.create();
        when(getClassroomService().instance(any())).thenReturn(classroom);
        
        ListCourseWorkResponse response = getCourseWorkService().
            searchCourseWorksByCourseId(classroom, "1");
        
        assertNotNull(response);
    }
    
    @Test
    public void shouldNotMapWhenFilterListCourseWorkResponseIsNull() {
        java.util.List<com.communify.api.model.CourseWork> list = getCourseWorkService()
            .courseWorksList(null, asList(of(ListCourseWorkResponse::new)
                .with(ListCourseWorkResponse::setCourseWork, null)
                .build()));
        assertNotNull(list);
        assertEquals(0, list.size());
    }
    
    private Classroom mockClassroomService(Boolean withReturnDate) throws Exception {
        Get get = mock(Get.class);
        when(get.execute()).thenReturn(ClassroomCourseResponseTestFactory.create());
        
        List classroomCourseWorksList = mock(List.class);
        when(classroomCourseWorksList.execute()).thenReturn(
            withReturnDate ? create() : createWithNoReturnDate());
        
        CourseWork courseWork = mock(CourseWork.class);
        when(courseWork.list(any())).thenReturn(classroomCourseWorksList);
        
        com.google.api.services.classroom.Classroom.Courses.List classroomCoursesList = 
            mock(com.google.api.services.classroom.Classroom.Courses.List.class);
        when(classroomCoursesList.execute()).thenReturn(
            ClassroomListCoursesResponseTestFactory.create());
            
        Courses courses = mock(Courses.class);
        when(courses.list()).thenReturn(classroomCoursesList);
        when(courses.courseWork()).thenReturn(courseWork);
        when(courses.get(any())).thenReturn(get);
        
        Classroom classroom = mock(Classroom.class);
        when(classroom.courses()).thenReturn(courses);
        return classroom;
    }

}
