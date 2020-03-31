package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistManager;
import com.cafeto.core.application.entity.Nephrologist;
import com.cafeto.core.application.entity.Nephrologist;
import com.cafeto.core.application.repository.NephrologistRespository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NephrologistManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class NephrologistManagerHandlerTestConfiguration {

        @Bean
        public NephrologistManager nephrologistManager() {
            return new NephrologistManagerHandler();
        }

    }

    @Autowired
    NephrologistManager nephrologistManager;



    @Before
    public void before(){
        List<Nephrologist> listNephrologistMock = new ArrayList<>();
        Nephrologist nephrologist = new Nephrologist();
        nephrologist.setName("Nephrologist");
        listNephrologistMock.add(nephrologist);
        when(nephrologistRespository.findAll()).thenReturn(listNephrologistMock);
        Nephrologist nephrologistSave = new Nephrologist();
        nephrologistSave.setId(0);
        nephrologistSave.setName("Nephrologist");
        when(nephrologistRespository.save(nephrologistSave)).thenReturn(nephrologistSave);
        Nephrologist nephrologistSaveException = new Nephrologist();
        nephrologistSaveException.setId(1);
        when(nephrologistRespository.save(nephrologistSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(nephrologistRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<Nephrologist>> nephrologistList = nephrologistManager.getAll();
        assertNotNull(nephrologistList);
        assertEquals(1, nephrologistList.getData().size());
        assertEquals("Nephrologist", nephrologistList.getData().get(0).getName());
    }

    @Test
    public void getAllException() {
        when(nephrologistRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<Nephrologist>> nephrologistList = nephrologistManager.getAll();
        assertNotNull(nephrologistList);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistList.getCode());
    }

    @Test
    public void save() {
        Nephrologist nephrologistSave = new Nephrologist();
        nephrologistSave.setId(0);
        nephrologistSave.setName("Nephrologist");
        ResponseEvent<Nephrologist> nephrologist = nephrologistManager.save(nephrologistSave);
        assertNotNull(nephrologist);
        assertEquals(0,nephrologist.getData().getId().intValue());
        assertEquals("Nephrologist", nephrologist.getData().getName());
    }

    @Test
    public void saveException() {
        Nephrologist nephrologistSave = new Nephrologist();
        nephrologistSave.setId(1);
        ResponseEvent<Nephrologist> nephrologist = nephrologistManager.save(nephrologistSave);
        assertNotNull(nephrologist);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologist.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> nephrologist = nephrologistManager.delete(new Integer(0));
        assertNotNull(nephrologist);
        assertEquals(ResponseCode.OK,nephrologist.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> nephrologist = nephrologistManager.delete(1);
        assertNotNull(nephrologist);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologist.getCode());
    }

}