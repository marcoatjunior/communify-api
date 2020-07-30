package com.communify.api.controller;

import static com.communify.api.factory.CourseWorkTestFactory.DUE_DATE;
import static com.communify.api.factory.LessonTestFactory.DEADLINE;
import static com.communify.api.helper.DateHelper.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.contract.ICourseWorkService;
import com.communify.api.contract.ILessonService;
import com.communify.api.dto.TaskDTO;
import com.communify.api.entity.Lesson;
import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.factory.CourseWorkTestFactory;
import com.communify.api.factory.LessonTestFactory;
import com.communify.api.helper.ClassroomDateHelper;
import com.communify.api.helper.MoodleDateHelper;
import com.communify.api.mapper.TaskMapper;
import com.communify.api.model.CourseWork;
import com.communify.api.model.Task;

import lombok.Getter;

@Getter
public class TaskControllerTest extends CommunifyApplicationTests {
    
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String EMAIL = "marcotaborda.jr@gmail.com";

    @InjectMocks
    private TaskController taskController;
    
    @Mock
    private ILessonService lessonService;
    
    @Mock
    private ICourseWorkService courseWorkService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldListTasksByEmail() {
        List<Lesson> lessonsList = asList(LessonTestFactory.create());
        when(getLessonService().list(any())).thenReturn(lessonsList);
        
        List<CourseWork> courseWorksList = asList(CourseWorkTestFactory.create());
        when(getCourseWorkService().list(any())).thenReturn(courseWorksList);
        
        List<TaskDTO> tasksList = getTaskController().list(ACCESS_TOKEN, EMAIL);
        List<Task> list = TaskMapper.dtosToModels(tasksList);
        
        assertNotNull(list);
        assertEquals(2, list.size());
        
        assertNotNull(list.get(0).getCourse());
        assertEquals(LessonTestFactory.ID.toString(), list.get(0).getId());
        assertEquals(LessonTestFactory.NAME, list.get(0).getDescription());
        assertEquals(format(MoodleDateHelper.toDate(DEADLINE)), 
            format(list.get(0).getReturnDate()));
        assertEquals(LessonTestFactory.ACTIVITY_LINK, list.get(0).getLink());
        assertEquals(TaskOriginEnum.Moodle, list.get(0).getOrigin());
        
        assertNull(list.get(1).getCourse());
        assertEquals(CourseWorkTestFactory.ID, list.get(1).getId());
        assertEquals(CourseWorkTestFactory.TITLE, list.get(1).getDescription());
        assertEquals(format(ClassroomDateHelper.toDate(DUE_DATE)), 
            format(list.get(1).getReturnDate()));
        assertEquals(CourseWorkTestFactory.ALTERNATE_LINK, list.get(1).getLink());
        assertEquals(TaskOriginEnum.Classroom, list.get(1).getOrigin());
        assertEquals(TaskOriginEnum.Classroom.getAmbienteVirtual(), list.get(1)
            .getOrigin().getAmbienteVirtual());
    }
    
    @Test
    public void shouldListTasksEvenIfReturnDateIsNull() {
        List<Lesson> lessonsList = asList(LessonTestFactory.createWithNoReturnDate());
        when(getLessonService().list(any())).thenReturn(lessonsList);
        
        List<CourseWork> courseWorksList = asList(CourseWorkTestFactory.createWithNoReturnDate());
        when(getCourseWorkService().list(any())).thenReturn(courseWorksList);
        
        List<TaskDTO> tasksList = getTaskController().list(ACCESS_TOKEN, EMAIL);
        List<Task> list = TaskMapper.dtosToModels(tasksList);
        
        assertNotNull(list);
        assertEquals(2, list.size());
        
        assertNotNull(list.get(1).getCourse());
        assertEquals(LessonTestFactory.ID.toString(), list.get(1).getId());
        assertEquals(LessonTestFactory.NAME, list.get(1).getDescription());
        assertEquals(format(MoodleDateHelper.toDate(null)), format(list.get(1).getReturnDate()));
        assertEquals(LessonTestFactory.ACTIVITY_LINK, list.get(1).getLink());
        assertEquals(TaskOriginEnum.Moodle, list.get(1).getOrigin());
        
        assertNull(list.get(0).getCourse());
        assertEquals(CourseWorkTestFactory.ID, list.get(0).getId());
        assertEquals(CourseWorkTestFactory.TITLE, list.get(0).getDescription());
        assertEquals(format(ClassroomDateHelper.toDate(null)), format(list.get(0).getReturnDate()));
        assertEquals(CourseWorkTestFactory.ALTERNATE_LINK, list.get(0).getLink());
        assertEquals(TaskOriginEnum.Classroom, list.get(0).getOrigin());
        assertEquals(TaskOriginEnum.Classroom.getAmbienteVirtual(), list.get(0)
            .getOrigin().getAmbienteVirtual());
    }
}
