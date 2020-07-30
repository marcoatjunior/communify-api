package com.communify.api.service;

import static com.communify.api.factory.UserTestFactory.create;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.entity.User;

import lombok.Getter;

@Getter
public class TermServiceTest extends CommunifyApplicationTests {
    
    private static final String EMAIL = "marcotaborda.jr@gmail.com";
    private static final String IP_ADDRESS = "192.168.0.1";

    @InjectMocks
    private TermService termService;
    
    @Mock
    private UserService userService;
    
    @Mock
    private MailService mailService;
    
    @Mock
    private JavaMailSender mailSender;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSendTerm() {
        User user = create();
        when(getUserService().findByClassroom(any())).thenReturn(user);
        
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(getMailSender().createMimeMessage()).thenReturn(mimeMessage);
        
        doNothing().when(getMailService()).create(any(), any(), any(), any(), any(), any());
        doNothing().when(getMailSender()).send(mimeMessage);
        
        getTermService().send(EMAIL, IP_ADDRESS);
    }
}
