package com.communify.api.controller;

import static com.communify.api.factory.LessonTestFactory.ACTIVITY_LINK;
import static com.communify.api.factory.LessonTestFactory.DEADLINE;
import static com.communify.api.factory.LessonTestFactory.ID;
import static com.communify.api.factory.LessonTestFactory.NAME;
import static com.communify.api.factory.LessonTestFactory.create;
import static com.communify.api.mapper.LessonMapper.modelToDTO;
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
import com.communify.api.contract.ILessonService;
import com.communify.api.dto.LessonDTO;
import com.communify.api.entity.Lesson;

import lombok.Getter;

@Getter
public class LessonControllerTest extends CommunifyApplicationTests {

    @InjectMocks
    private LessonController lessonController;
    
    @Mock
    private ILessonService lessonService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSaveLessonsList() {
        Lesson lesson = create();
        when(getLessonService().save(any())).thenReturn(lesson);
        
        List<LessonDTO> list = getLessonController().save(
            asList(modelToDTO(lesson)));
        
        assertEquals(1, list.size());
        assertEquals(ID, lesson.getId());
        assertEquals(NAME, lesson.getName());
        assertEquals(DEADLINE, lesson.getDeadline());
        assertEquals(ACTIVITY_LINK, lesson.getActivityLink());
    }
}
