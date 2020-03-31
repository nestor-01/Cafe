package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ZoneManager;
import com.cafeto.core.application.entity.Zone;
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
public class ZoneManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class ZoneManagerHandlerTestConfiguration {

        @Bean
        public ZoneManager zoneManager() {
            return new ZoneManagerHandler();
        }

    }

    @Autowired
    ZoneManager zoneManager;

    @Before
    public void before(){
        List<Zone> listZoneMock = new ArrayList<>();
        Zone zone = new Zone();
        zone.setDescription("Zone");
        listZoneMock.add(zone);
        when(zoneRespository.findAll()).thenReturn(listZoneMock);
        Zone zoneSave = new Zone();
        zoneSave.setId(0);
        zoneSave.setDescription("Zone");
        when(zoneRespository.save(zoneSave)).thenReturn(zoneSave);
        Zone zoneSaveException = new Zone();
        zoneSaveException.setId(1);
        when(zoneRespository.save(zoneSaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(zoneRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<Zone>> zoneList = zoneManager.getAll();
        assertNotNull(zoneList);
        assertEquals(1, zoneList.getData().size());
        assertEquals("Zone", zoneList.getData().get(0).getDescription());
    }

    @Test
    public void getAllException() {
        when(zoneRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<Zone>> zoneList = zoneManager.getAll();
        assertNotNull(zoneList);
        assertEquals(ResponseCode.APPLICATION_ERROR, zoneList.getCode());
    }

    @Test
    public void save() {
        Zone zoneSave = new Zone();
        zoneSave.setId(0);
        zoneSave.setDescription("Zone");
        ResponseEvent<Zone> zone = zoneManager.save(zoneSave);
        assertNotNull(zone);
        assertEquals(0,zone.getData().getId().intValue());
        assertEquals("Zone", zone.getData().getDescription());
    }

    @Test
    public void saveException() {
        Zone zoneSave = new Zone();
        zoneSave.setId(1);
        ResponseEvent<Zone> zone = zoneManager.save(zoneSave);
        assertNotNull(zone);
        assertEquals(ResponseCode.APPLICATION_ERROR, zone.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> zone = zoneManager.delete(new Integer(0));
        assertNotNull(zone);
        assertEquals(ResponseCode.OK,zone.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> zone = zoneManager.delete(1);
        assertNotNull(zone);
        assertEquals(ResponseCode.APPLICATION_ERROR, zone.getCode());
    }

}