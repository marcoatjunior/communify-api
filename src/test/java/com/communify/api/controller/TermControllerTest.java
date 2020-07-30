package com.communify.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.contract.ITermService;

import lombok.Getter;

@Getter
public class TermControllerTest extends CommunifyApplicationTests {
    
    private static final String EMAIL = "marcotaborda.jr@gmail.com";
    private static final String IP_ADDRESS = "192.168.0.1";

    @InjectMocks
    private TermController termController;
    
    @Mock
    private ITermService termService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldSendTermByEmail() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn(IP_ADDRESS);
        
        doNothing().when(getTermService()).send(any(), any());
        getTermController().send(EMAIL, request);
        verify(getTermService(), times(1)).send(any(), any());
    }
}
