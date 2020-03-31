package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ClinicTypeManager;
import com.cafeto.core.application.entity.ClinicType;
import com.cafeto.core.application.entity.ClinicType;
import com.cafeto.core.application.repository.ClinicTypeRespository;
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
public class ClinicTypeManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class ClinicTypeManagerHandlerTestConfiguration {

        @Bean
        public ClinicTypeManager clinicTypeManager() {
            return new ClinicTypeManagerHandler();
        }

    }

    @Autowired
    ClinicTypeManager clinicTypeManager;

    @Before
    public void before(){
        List<ClinicType> listClinicTypeMock = new ArrayList<>();
        ClinicType clinicType = new ClinicType();
        clinicType.setDescription("ClinicType");
        listClinicTypeMock.add(clinicType);
        when(clinicTypeRespository.findAll()).thenReturn(listClinicTypeMock);
        ClinicType clinicTypeSave = new ClinicType();
        clinicTypeSave.setId(0);
        clinicTypeSave.setDescription("ClinicType");
        when(clinicTypeRespository.save(clinicTypeSave)).thenReturn(clinicTypeSave);
        ClinicType clinicTypeSaveException = new ClinicType();
        clinicTypeSaveException.setId(1);
        when(clinicTypeRespository.save(clinicTypeSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(clinicTypeRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<ClinicType>> clinicTypeList = clinicTypeManager.getAll();
        assertNotNull(clinicTypeList);
        assertEquals(1, clinicTypeList.getData().size());
        assertEquals("ClinicType", clinicTypeList.getData().get(0).getDescription());
    }

    @Test
    public void getAllException() {
        when(clinicTypeRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<ClinicType>> clinicTypeList = clinicTypeManager.getAll();
        assertNotNull(clinicTypeList);
        assertEquals(ResponseCode.APPLICATION_ERROR, clinicTypeList.getCode());
    }

    @Test
    public void save() {
        ClinicType clinicTypeSave = new ClinicType();
        clinicTypeSave.setId(0);
        clinicTypeSave.setDescription("ClinicType");
        ResponseEvent<ClinicType> clinicType = clinicTypeManager.save(clinicTypeSave);
        assertNotNull(clinicType);
        assertEquals(0,clinicType.getData().getId().intValue());
        assertEquals("ClinicType", clinicType.getData().getDescription());
    }

    @Test
    public void saveException() {
        ClinicType clinicTypeSave = new ClinicType();
        clinicTypeSave.setId(1);
        ResponseEvent<ClinicType> clinicType = clinicTypeManager.save(clinicTypeSave);
        assertNotNull(clinicType);
        assertEquals(ResponseCode.APPLICATION_ERROR, clinicType.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> clinicType = clinicTypeManager.delete(new Integer(0));
        assertNotNull(clinicType);
        assertEquals(ResponseCode.OK,clinicType.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> clinicType = clinicTypeManager.delete(1);
        assertNotNull(clinicType);
        assertEquals(ResponseCode.APPLICATION_ERROR, clinicType.getCode());
    }

}