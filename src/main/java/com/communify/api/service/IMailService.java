package com.communify.api.service;

import javax.mail.internet.MimeMessage;

import com.communify.api.dto.TaskDTO;
import com.communify.api.model.User;

public interface IMailService {

    void create(
        User user, 
        TaskDTO task, 
        String ip, 
        String templateFile, 
        String subjectMessage, 
        MimeMessage message
    );
}
