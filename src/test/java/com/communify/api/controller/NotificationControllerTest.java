package com.communify.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.contract.INotificationService;

import lombok.Getter;

@Getter
public class NotificationControllerTest extends CommunifyApplicationTests {
    
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String EMAIL = "marcotaborda.jr@gmail.com";

    @InjectMocks
    private NotificationController notificationController;
    
    @Mock
    private INotificationService notificationService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSendNotificationsByEmail() {
        doNothing().when(getNotificationService()).send(any(), any());
        getNotificationController().send(ACCESS_TOKEN, EMAIL);
        verify(getNotificationService(), times(1)).send(any(), any());
    }
}
