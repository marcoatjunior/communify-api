package com.communify.api.service;

import static com.communify.api.helper.DateHelper.transform;
import static java.time.LocalDate.now;
import static java.time.ZoneId.systemDefault;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.communify.api.contracts.ICourseWorkService;
import com.communify.api.contracts.ILessonService;
import com.communify.api.contracts.IMailService;
import com.communify.api.contracts.ITaskNotificationService;
import com.communify.api.contracts.IUserService;
import com.communify.api.dto.TaskDTO;
import com.communify.api.mapper.CourseWorkMapper;
import com.communify.api.mapper.LessonMapper;
import com.communify.api.model.CourseWork;
import com.communify.api.model.Lesson;
import com.communify.api.model.User;
import com.google.api.services.classroom.model.Date;

import lombok.Getter;

@Service
@Getter
public class TaskNotificationService implements ITaskNotificationService {
    
    private static final Long NUMBER_OF_DAYS_IN_WEEK = 7L;
    private static final String TEMPLATE_FILE = "notification.vm";
    private static final String DEFAULT_SUBJECT_MESSAGE = "Tarefa com entrega nesta semana!";
    
    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseWorkService courseWorkService;
    
    @Autowired
    private ILessonService lessonService;
    
    @Autowired
    private IMailService mailService;
    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(String accessToken, String email) {
        User user = getUserService().findByClassroom(email);
        sendClassroom(accessToken, user);
        sendMoodle(user);
    }

    private void sendClassroom(String accessToken, User user) {
        List<CourseWork> courseWorksList = getCourseWorkService().list(accessToken);
        courseWorksList.stream()
            .filter(courseWork -> daysBetween(courseWork.getDueDate()) <= NUMBER_OF_DAYS_IN_WEEK)
            .forEach(courseWork -> shoot(user, CourseWorkMapper.modelToDTO(courseWork)));
    }
    
    private Long daysBetween(Date dueDate) {
        return DAYS.between(now(), convertToLocalDate(transform(dueDate)));
    }
    
    private void sendMoodle(User user) {
        List<Lesson> lessonsList = getLessonService().list(user.getMoodleEmailAddress());
        lessonsList.stream()
            .filter(lesson -> daysBetween(lesson.getDeadline()) <= NUMBER_OF_DAYS_IN_WEEK)
            .forEach(lesson -> shoot(user, LessonMapper.modelToDTO(lesson)));
    }
    
    private Long daysBetween(Long time) {
        return DAYS.between(now(), convertToLocalDate(transform(time)));
    }
    
    private LocalDate convertToLocalDate(java.util.Date date) {
        return date.toInstant().atZone(systemDefault()).toLocalDate();
    }
    
    private void shoot(User user, TaskDTO task) {
        MimeMessage message = getMailSender().createMimeMessage();
        getMailService().create(user, task, null, 
                TEMPLATE_FILE, DEFAULT_SUBJECT_MESSAGE, message);
        getMailSender().send(message);
    }
}
