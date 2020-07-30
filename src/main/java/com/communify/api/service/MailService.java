package com.communify.api.service;

import static javax.mail.internet.InternetAddress.parse;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.communify.api.contract.IMailService;
import com.communify.api.entity.User;
import com.communify.api.model.Task;

import lombok.Getter;

@Service
@Getter
public class MailService implements IMailService {
    
    private static final String DEFAULT_SUBJECT_SENDER  = "communify.mail@gmail.com";

    @Autowired
    private VelocityEngine velocityEngine;
    
    public void create(
        User user, 
        Task task, 
        String ip, 
        String templateFile, 
        String subjectMessage, 
        MimeMessage message
    ) {
        try {
            MimeMessageHelper messageHelper = instanceMimeMessageHelper(message);
            messageHelper.setFrom(DEFAULT_SUBJECT_SENDER);
            messageHelper.setTo(parseReceivers(user));
            messageHelper.setSubject(subjectMessage);
            messageHelper.setText(getContent(user, task, ip, templateFile), true);
        } catch (MessagingException e) {
            e.getStackTrace();
        }
    }

    protected MimeMessageHelper instanceMimeMessageHelper(MimeMessage message) 
        throws MessagingException {
        return new MimeMessageHelper(message);
    }
    
    protected InternetAddress[] parseReceivers(User user) {
        try {
            return parse(new StringBuilder()
                .append(user.getClassroomEmailAddress())
                .append(",")
                .append(user.getMoodleEmailAddress())
                .toString());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
    
    protected String getContent(User user, Task task, String ip, String templateFile) {
        StringWriter writer = new StringWriter();
        getVelocityEngine()
            .mergeTemplate(templateFile, "UTF-8", createContext(user, task, ip), writer);
        return writer.toString();
    }

    protected VelocityContext createContext(User user, Task task, String ip) {
        VelocityContext context = new VelocityContext();
        context.put("user", user);
        context.put("task", task);
        context.put("ip", ip);
        return context;
    }
}
