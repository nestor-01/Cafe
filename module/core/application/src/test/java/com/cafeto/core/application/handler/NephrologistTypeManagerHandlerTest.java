package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistTypeManager;
import com.cafeto.core.application.entity.NephrologistType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
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
public class NephrologistTypeManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class NephrologistTypeManagerHandlerTestConfiguration {

        @Bean
        public NephrologistTypeManager nephrologistTypeManager() {
            return new NephrologistTypeManagerHandler();
        }

    }

    @Autowired
    NephrologistTypeManager nephrologistTypeManager;



    @Before
    public void before(){
        List<NephrologistType> listNephrologistTypeMock = new ArrayList<>();
        NephrologistType nephrologistType = new NephrologistType();
        nephrologistType.setDescription("NephrologistType");
        listNephrologistTypeMock.add(nephrologistType);
        when(nephrologistTypeRespository.findAll()).thenReturn(listNephrologistTypeMock);
        NephrologistType nephrologistTypeSave = new NephrologistType();
        nephrologistTypeSave.setId(0);
        nephrologistTypeSave.setDescription("NephrologistType");
        when(nephrologistTypeRespository.save(nephrologistTypeSave)).thenReturn(nephrologistTypeSave);
        NephrologistType nephrologistTypeSaveException = new NephrologistType();
        nephrologistTypeSaveException.setId(1);
        when(nephrologistTypeRespository.save(nephrologistTypeSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(nephrologistTypeRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<NephrologistType>> nephrologistTypeList = nephrologistTypeManager.getAll();
        assertNotNull(nephrologistTypeList);
        assertEquals(1, nephrologistTypeList.getData().size());
        assertEquals("NephrologistType", nephrologistTypeList.getData().get(0).getDescription());
    }

    @Test
    public void getAllException() {
        when(nephrologistTypeRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<NephrologistType>> nephrologistTypeList = nephrologistTypeManager.getAll();
        assertNotNull(nephrologistTypeList);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistTypeList.getCode());
    }

    @Test
    public void save() {
        NephrologistType nephrologistTypeSave = new NephrologistType();
        nephrologistTypeSave.setId(0);
        nephrologistTypeSave.setDescription("NephrologistType");
        ResponseEvent<NephrologistType> nephrologistType = nephrologistTypeManager.save(nephrologistTypeSave);
        assertNotNull(nephrologistType);
        assertEquals(0,nephrologistType.getData().getId().intValue());
        assertEquals("NephrologistType", nephrologistType.getData().getDescription());
    }

    @Test
    public void saveException() {
        NephrologistType nephrologistTypeSave = new NephrologistType();
        nephrologistTypeSave.setId(1);
        ResponseEvent<NephrologistType> nephrologistType = nephrologistTypeManager.save(nephrologistTypeSave);
        assertNotNull(nephrologistType);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistType.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> nephrologistType = nephrologistTypeManager.delete(new Integer(0));
        assertNotNull(nephrologistType);
        assertEquals(ResponseCode.OK,nephrologistType.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> nephrologistType = nephrologistTypeManager.delete(1);
        assertNotNull(nephrologistType);
        assertEquals(ResponseCode.APPLICATION_ERROR, nephrologistType.getCode());
    }

}