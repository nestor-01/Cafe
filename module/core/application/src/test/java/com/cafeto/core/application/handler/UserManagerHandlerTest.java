package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.UserManager;
import com.cafeto.core.application.entity.User;
import com.cafeto.core.application.entity.User;
import com.cafeto.core.application.repository.UserRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class UserManagerHandlerTestConfiguration {

        @Bean
        public UserManager userManager() {
            return new UserManagerHandler();
        }

    }

    @Autowired
    UserManager userManager;



    @Before
    public void before(){
        List<User> listUserMock = new ArrayList<>();
        User user = new User();
        user.setName("User");
        listUserMock.add(user);
        when(userRespository.findAll()).thenReturn(listUserMock);
        User userSave = new User();
        userSave.setId(0);
        userSave.setName("User");
        when(userRespository.save(userSave)).thenReturn(userSave);
        User userSaveException = new User();
        userSaveException.setId(1);
        when(userRespository.save(userSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(userRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<User>> userList = userManager.getAll();
        assertNotNull(userList);
        assertEquals(1, userList.getData().size());
        assertEquals("User", userList.getData().get(0).getName());
    }

    @Test
    public void getAllException() {
        when(userRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<User>> userList = userManager.getAll();
        assertNotNull(userList);
        assertEquals(ResponseCode.APPLICATION_ERROR, userList.getCode());
    }

    @Test
    public void save() {
        User userSave = new User();
        userSave.setId(0);
        userSave.setName("User");
        ResponseEvent<User> user = userManager.save(userSave);
        assertNotNull(user);
        assertEquals(0,user.getData().getId().intValue());
        assertEquals("User", user.getData().getName());
    }

    @Test
    public void saveException() {
        User userSave = new User();
        userSave.setId(1);
        ResponseEvent<User> user = userManager.save(userSave);
        assertNotNull(user);
        assertEquals(ResponseCode.APPLICATION_ERROR, user.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> user = userManager.delete(new Integer(0));
        assertNotNull(user);
        assertEquals(ResponseCode.OK,user.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> user = userManager.delete(1);
        assertNotNull(user);
        assertEquals(ResponseCode.APPLICATION_ERROR, user.getCode());
    }

}