package com.communify.api.service;

import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.entity.Lesson;
import com.communify.api.factory.LessonTestFactory;
import com.communify.api.repository.LessonRepository;

import lombok.Getter;

@Getter
public class LessonServiceTest extends CommunifyApplicationTests {
    
    private static final String EMAIL = "marcotaborda.jr@gmail.com";
    
    @InjectMocks
    private LessonService lessonService;
    
    @Mock
    private LessonRepository lessonRepository;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldListLessonsByEmail() {
        List<Lesson> lessonsList = asList(LessonTestFactory.create());
        when(getLessonRepository().findByEmail(any())).thenReturn(lessonsList);
        
        List<Lesson> lessonsListReturned = getLessonService().list(EMAIL);
        
        assertNotNull(lessonsListReturned);
        assertEquals(1, lessonsListReturned.size());
    }
    
    @Test
    public void shouldListLessonsByEmailEvenIfReturnDateIsNull() {
        List<Lesson> lessonsList = asList(LessonTestFactory.createWithNoReturnDate());
        when(getLessonRepository().findByEmail(any())).thenReturn(lessonsList);
        
        List<Lesson> lessonsListReturned = getLessonService().list(EMAIL);
        
        assertNotNull(lessonsListReturned);
        assertEquals(1, lessonsListReturned.size());
    }
    
    @Test
    public void shouldSaveNewCourse() {
        Lesson lesson = LessonTestFactory.create();
        when(getLessonRepository().save(any())).thenReturn(lesson);
        
        Lesson userReturned = getLessonService().save(lesson);
        
        assertEquals(lesson.getId(), userReturned.getId());
        assertEquals(lesson.getName(), userReturned.getName());
    }
}
