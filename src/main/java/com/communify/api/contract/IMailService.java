package com.communify.api.contract;

import javax.mail.internet.MimeMessage;

import com.communify.api.entity.User;
import com.communify.api.model.Task;

public interface IMailService {

    void create(
        User user, 
        Task task, 
        String ip, 
        String templateFile, 
        String subjectMessage, 
        MimeMessage message
    );
}
