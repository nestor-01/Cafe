package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ClinicManager;
import com.cafeto.core.application.entity.Clinic;
import com.cafeto.core.application.repository.ClinicRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ClinicManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class ClinicManagerHandlerTestConfiguration {

        @Bean
        public ClinicManager clinicManager() {
            return new ClinicManagerHandler();
        }

    }

    @Autowired
    ClinicManager clinicManager;



    @Before
    public void before(){
        List<Clinic> listClinicMock = new ArrayList<>();
        Clinic clinic = new Clinic();
        clinic.setName("Clinic");
        listClinicMock.add(clinic);
        when(clinicRespository.findAll()).thenReturn(listClinicMock);
        Clinic clinicSave = new Clinic();
        clinicSave.setId(0);
        clinicSave.setName("Clinic");
        when(clinicRespository.save(clinicSave)).thenReturn(clinicSave);
        Clinic clinicSaveException = new Clinic();
        clinicSaveException.setId(1);
        when(clinicRespository.save(clinicSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(clinicRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<Clinic>> clinicList = clinicManager.getAll();
        assertNotNull(clinicList);
        assertEquals(1, clinicList.getData().size());
        assertEquals("Clinic", clinicList.getData().get(0).getName());
    }

    @Test
    public void getAllException() {
        when(clinicRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<Clinic>> clinicList = clinicManager.getAll();
        assertNotNull(clinicList);
        assertEquals(ResponseCode.APPLICATION_ERROR, clinicList.getCode());
    }

    @Test
    public void save() {
        Clinic clinicSave = new Clinic();
        clinicSave.setId(0);
        clinicSave.setName("Clinic");
        ResponseEvent<Clinic> clinic = clinicManager.save(clinicSave);
        assertNotNull(clinic);
        assertEquals(0,clinic.getData().getId().intValue());
        assertEquals("Clinic", clinic.getData().getName());
    }

    @Test
    public void saveException() {
        Clinic clinicSave = new Clinic();
        clinicSave.setId(1);
        ResponseEvent<Clinic> clinic = clinicManager.save(clinicSave);
        assertNotNull(clinic);
        assertEquals(ResponseCode.APPLICATION_ERROR, clinic.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> clinic = clinicManager.delete(new Integer(0));
        assertNotNull(clinic);
        assertEquals(ResponseCode.OK,clinic.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> clinic = clinicManager.delete(1);
        assertNotNull(clinic);
        assertEquals(ResponseCode.APPLICATION_ERROR, clinic.getCode());
    }


}