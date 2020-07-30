package com.communify.api.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.entity.Lesson;
import com.communify.api.entity.User;
import com.communify.api.factory.CourseWorkTestFactory;
import com.communify.api.factory.LessonTestFactory;
import com.communify.api.factory.UserTestFactory;
import com.communify.api.model.CourseWork;

import lombok.Getter;

@Getter
public class NotificationServiceTest extends CommunifyApplicationTests {
    
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String EMAIL = "marcotaborda.jr@gmail.com";

    @InjectMocks
    private NotificationService notificationService;
    
    @Mock
    private UserService userService;
    
    @Mock
    private CourseWorkService courseWorkService;
    
    @Mock
    private LessonService lessonService;
    
    @Mock
    private MailService mailService;
    
    @Mock
    private JavaMailSender mailSender;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSendNotification() {
        User user = UserTestFactory.create();
        when(getUserService().findByClassroom(EMAIL)).thenReturn(user);
        
        List<Lesson> lessonsList = asList(LessonTestFactory.create());
        when(getLessonService().list(any())).thenReturn(lessonsList);
        
        List<CourseWork> courseWorksList = asList(CourseWorkTestFactory.create());
        when(getCourseWorkService().list(any())).thenReturn(courseWorksList);
        
        MimeMessage mimeMessage = mock(MimeMessage.class);
        doNothing().when(getMailSender()).send(mimeMessage);
        doNothing().when(getMailService()).create(any(), any(), any(), any(), any(), any());
        
        getNotificationService().send(ACCESS_TOKEN, EMAIL);
    }
}
