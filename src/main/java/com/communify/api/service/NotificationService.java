package com.communify.api.service;

import static com.communify.api.helper.DateHelper.compare;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.communify.api.contract.ICourseWorkService;
import com.communify.api.contract.ILessonService;
import com.communify.api.contract.IMailService;
import com.communify.api.contract.INotificationService;
import com.communify.api.contract.IUserService;
import com.communify.api.dto.TaskDTO;
import com.communify.api.mapper.CourseWorkToTaskMapper;
import com.communify.api.mapper.LessonToTaskMapper;
import com.communify.api.model.CourseWork;
import com.communify.api.model.Lesson;
import com.communify.api.model.User;

import lombok.Getter;

@Service
@Getter
public class NotificationService implements INotificationService {
    
    private static final String TEMPLATE_FILE = "notification.vm";
    private static final String DEFAULT_SUBJECT_MESSAGE = "Tarefa com entrega nesta semana";
    
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
            .filter(courseWork -> compare(courseWork.getDueDate()))
            .forEach(courseWork -> shoot(user, CourseWorkToTaskMapper.modelToDTO(courseWork)));
    }
    
    private void sendMoodle(User user) {
        List<Lesson> lessonsList = getLessonService().list(user.getMoodleEmailAddress());
        lessonsList.stream()
            .filter(lesson -> compare(lesson.getDeadline()))
            .forEach(lesson -> shoot(user, LessonToTaskMapper.modelToDTO(lesson)));
    }
    
    private void shoot(User user, TaskDTO task) {
        MimeMessage message = getMailSender().createMimeMessage();
        getMailService().create(user, task, null, 
                TEMPLATE_FILE, getSubject(task), message);
        getMailSender().send(message);
    }
    
    private String getSubject(TaskDTO task) {
        return new StringBuilder()
            .append(task.getCourse())
            .append(": ")
            .append(DEFAULT_SUBJECT_MESSAGE)
            .toString();
    }
}
