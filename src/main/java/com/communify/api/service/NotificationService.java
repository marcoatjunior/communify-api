package com.communify.api.service;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.DateHelper.transform;
import static java.time.LocalDate.now;
import static java.time.ZoneId.systemDefault;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.api.services.classroom.model.CourseWork;
import com.google.api.services.classroom.model.Date;

import lombok.Getter;

@Service
@Getter
public class NotificationService implements INotificationService {
    
    private static final Long NUMBER_OF_DAYS_IN_WEEK = 7L;
    private static final String DEFAULT_SUBJECT_SENDER = "communify@unilasalle.edu.br";
    private static final String DEFAULT_SUBJECT_MESSAGE = "Communify informa: Tarefa com entrega nesta semana!";

    @Autowired
    private ICourseWorkService courseWorkService;
    
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void send(String accessToken, String email) {
        List<CourseWork> listCourseWork = getCourseWorkService().list(accessToken);
        listCourseWork.stream()
            .filter(courseWork -> daysBetween(courseWork.getDueDate()) <= NUMBER_OF_DAYS_IN_WEEK)
            .forEach(courseWork -> shoot(email, courseWork.getAlternateLink()));
    }
    
    private Long daysBetween(Date dueDate) {
        return DAYS.between(now(), convertToLocalDate(transform(dueDate)));
    }
    
    private LocalDate convertToLocalDate(java.util.Date date) {
        return date.toInstant().atZone(systemDefault()).toLocalDate();
    }
    
    private void shoot(String receiver, String link) {
        getEmailSender().send(of(SimpleMailMessage::new)
            .with(SimpleMailMessage::setFrom, DEFAULT_SUBJECT_SENDER)
            .with(SimpleMailMessage::setSubject, DEFAULT_SUBJECT_MESSAGE)
            .with(SimpleMailMessage::setTo, receiver)
            .with(SimpleMailMessage::setText, link)
            .build());
    }
}
