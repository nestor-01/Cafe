package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistBaseClinicManager;
import com.cafeto.core.application.entity.NephrologistBaseClinic;
import com.cafeto.core.application.entity.NephrologistBaseClinic;
import com.cafeto.core.application.repository.NephrologistBaseClinicRespository;
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
public class NephrologistBaseClinicManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class NephrologistBaseCLinicManagerHandlerTestConfiguration {

        @Bean
        public NephrologistBaseClinicManager nephrologistBaseClinicManager() {
            return new NephrologistBaseClinicManagerHandler();
        }

    }

    @Autowired
    NephrologistBaseClinicManager nephrologistBaseClinicManager;


    @Before
    public void before(){
        List<NephrologistBaseClinic> listNephrologistBaseCLinicMock = new ArrayList<>();
        NephrologistBaseClinic nephrologistBaseClinic = new NephrologistBaseClinic();
        nephrologistBaseClinic.setDedicationHours(5);
        listNephrologistBaseCLinicMock.add(nephrologistBaseClinic);
        when(nephrologistBaseClinicRespository.findAll()).thenReturn(listNephrologistBaseCLinicMock);
        NephrologistBaseClinic nephrologistBaseClinicSave = new NephrologistBaseClinic();
        nephrologistBaseClinicSave.setId(0);
        nephrologistBaseClinicSave.setDedicationHours(5);
        when(nephrologistBaseClinicRespository.save(nephrologistBaseClinicSave)).thenReturn(nephrologistBaseClinicSave);
        NephrologistBaseClinic nephrologistBaseClinicSaveException = new NephrologistBaseClinic();
        nephrologistBaseClinicSaveException.setId(1);
        when(nephrologistBaseClinicRespository.save(nephrologistBaseClinicSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(nephrologistBaseClinicRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<NephrologistBaseClinic>> nephrologistBaseClinicList = nephrologistBaseClinicManager.getAll();
        assertNotNull(nephrologistBaseClinicList);
        assertEquals(1, nephrologistBaseClinicList.getData().size());
        assertEquals(5, nephrologistBaseClinicList.getData().get(0).getDedicationHours().intValue());
    }

    @Test
    public void getAllException() {
        when(nephrologistBaseClinicRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<NephrologistBaseClinic>> nephrologistBaseClinicList = nephrologistBaseClinicManager.getAll();
        assertNotNull(nephrologistBaseClinicList);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistBaseClinicList.getCode());
    }

    @Test
    public void save() {
        NephrologistBaseClinic nephrologistBaseClinicSave = new NephrologistBaseClinic();
        nephrologistBaseClinicSave.setId(0);
        nephrologistBaseClinicSave.setDedicationHours(5);
        ResponseEvent<NephrologistBaseClinic> nephrologistBaseClinic = nephrologistBaseClinicManager.save(nephrologistBaseClinicSave);
        assertNotNull(nephrologistBaseClinic);
        assertEquals(0,nephrologistBaseClinic.getData().getId().intValue());
        assertEquals(5, nephrologistBaseClinic.getData().getDedicationHours().intValue());
    }

    @Test
    public void saveException() {
        NephrologistBaseClinic nephrologistBaseClinicSave = new NephrologistBaseClinic();
        nephrologistBaseClinicSave.setId(1);
        ResponseEvent<NephrologistBaseClinic> nephrologistBaseClinic = nephrologistBaseClinicManager.save(nephrologistBaseClinicSave);
        assertNotNull(nephrologistBaseClinic);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistBaseClinic.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> nephrologistBaseClinic = nephrologistBaseClinicManager.delete(new Integer(0));
        assertNotNull(nephrologistBaseClinic);
        assertEquals(ResponseCode.OK,nephrologistBaseClinic.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> nephrologistBaseClinic = nephrologistBaseClinicManager.delete(1);
        assertNotNull(nephrologistBaseClinic);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistBaseClinic.getCode());
    }

}