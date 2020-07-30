package com.communify.api.controller;

import static com.communify.api.factory.UserTestFactory.CLASSROOM_EMAIL_ADDRESS;
import static com.communify.api.factory.UserTestFactory.ID;
import static com.communify.api.factory.UserTestFactory.MOODLE_EMAIL_ADDRESS;
import static com.communify.api.factory.UserTestFactory.NAME;
import static com.communify.api.factory.UserTestFactory.URL_PHOTO;
import static com.communify.api.mapper.UserMapper.modelToDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.contract.IUserService;
import com.communify.api.dto.UserDTO;
import com.communify.api.entity.User;
import com.communify.api.factory.UserTestFactory;

import lombok.Getter;

@Getter
public class UserControllerTest extends CommunifyApplicationTests {
    
    private static final String EMAIL = "marcotaborda.jr@gmail.com";
    private static final String IP_ADDRESS = "192.168.0.1";

    @InjectMocks
    private UserController userController;
    
    @Mock
    private IUserService userService;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldFindUserByClassroom() throws IOException {
        User user = UserTestFactory.create();
        when(getUserService().findByClassroom(any())).thenReturn(user);
        
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn(IP_ADDRESS);
        
        UserDTO userReturned = getUserController().findByClassroom(EMAIL, request);
        assertValues(userReturned);
    }

    @Test
    public void shouldSaveNewUser() {
        User user = UserTestFactory.create();
        when(getUserService().save(any())).thenReturn(user);
        
        UserDTO userReturned = getUserController().save(modelToDTO(user));
        assertValues(userReturned);
    }
    
    private void assertValues(UserDTO userReturned) {
        assertEquals(ID, userReturned.getId());
        assertEquals(NAME, userReturned.getName());
        assertEquals(CLASSROOM_EMAIL_ADDRESS, userReturned.getClassroomEmailAddress());
        assertEquals(MOODLE_EMAIL_ADDRESS, userReturned.getMoodleEmailAddress());
        assertEquals(URL_PHOTO, userReturned.getUrlPhoto());
    }
}
