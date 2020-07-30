package com.communify.api.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.entity.User;
import com.communify.api.factory.TaskTestFactory;
import com.communify.api.factory.UserTestFactory;
import com.communify.api.model.Task;

import lombok.Getter;

@Getter
public class MailServiceTest extends CommunifyApplicationTests {
    
    private static final String IP_ADDRESS = "192.168.0.1";
    private static final String TEMPLATE_FILE = "notification.vm";
    private static final String SUBJECT_MESSAGE = "Assunto da mensagem";

    @Spy
    @InjectMocks
    private MailService mailService;
    
    @Mock
    private VelocityEngine velocityEngine;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldCreateMail() {
        User user = UserTestFactory.create();
        Task task = TaskTestFactory.create();
        MimeMessage mimeMessage = mock(MimeMessage.class);
        
        createMail(user, task, mimeMessage);
        verify(getVelocityEngine(), times(1)).mergeTemplate(any(), any(), any(), any());
        
        assertNotNull(task.getFormattedDate());
    }

    @Test
    public void shouldReturnNullWhenReceiversAreNull() {
        getMailService().parseReceivers(null);
        verify(getMailService(), times(1)).parseReceivers(any());
    }
    
    @Test
    public void shouldThrowMessageExceptionWhenEngineIsWrong() throws MessagingException {
        User user = UserTestFactory.create();
        Task task = TaskTestFactory.create();
        MimeMessage mimeMessage = mock(MimeMessage.class);

        doThrow(MessagingException.class)
            .when(getMailService()).instanceMimeMessageHelper(any());
        
        createMail(user, task, mimeMessage);
        verify(getMailService(), times(1)).instanceMimeMessageHelper(any());
    }
    
    protected void createMail(User user, Task task, MimeMessage mimeMessage) {
        getMailService().create(
            user, task, IP_ADDRESS, TEMPLATE_FILE, SUBJECT_MESSAGE, mimeMessage);
    }
}
