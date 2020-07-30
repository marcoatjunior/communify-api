package com.communify.api.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.communify.api.CommunifyApplicationTests;
import com.communify.api.entity.User;
import com.communify.api.factory.UserTestFactory;
import com.communify.api.repository.UserRepository;

import lombok.Getter;

@Getter
public class UserServiceTest extends CommunifyApplicationTests {
    
    @InjectMocks
    private UserService userService;
    
    @Mock
    private UserRepository userRepository;
    
    @Before
    public void init() {
        initMocks(this);
    }
    
    @Test
    public void shouldFindUserByClassroom() {
        User user = UserTestFactory.create();
        when(getUserRepository().findByClassroom(any())).thenReturn(user);
        
        User userReturned = getUserService().findByClassroom(user.getClassroomEmailAddress());
        
        assertEquals(user.getId(), userReturned.getId());
        assertEquals(user.getName(), userReturned.getName());
    }
    
    @Test
    public void shouldSaveNewUser() {
        User user = UserTestFactory.create();
        when(getUserRepository().save(any())).thenReturn(user);
        
        User userReturned = getUserService().save(user);
        
        assertEquals(user.getId(), userReturned.getId());
        assertEquals(user.getName(), userReturned.getName());
    }
}
