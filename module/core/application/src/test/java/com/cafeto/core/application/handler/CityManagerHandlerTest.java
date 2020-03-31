package com.cafeto.core.application.handler;

import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.CityManager;
import com.cafeto.core.application.entity.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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
public class CityManagerHandlerTest extends BeansMock{

    @TestConfiguration
    static class CityManagerHandlerTestConfiguration {

        @Bean
        public CityManager cityManager() {
            return new CityManagerHandler();
        }

    }

    @Autowired
    CityManager cityManager;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        List<City> listCityMock = new ArrayList<>();
        City city = new City();
        city.setDescription("City");
        listCityMock.add(city);
        when(cityRespository.findAll()).thenReturn(listCityMock);
        City citySave = new City();
        citySave.setId(0);
        citySave.setDescription("City");
        when(cityRespository.save(citySave)).thenReturn(citySave);
        City citySaveException = new City();
        citySaveException.setId(1);
        when(cityRespository.save(citySaveException)).thenThrow(new NullPointerException());
        doThrow(new NullPointerException()).when(cityRespository).deleteById(1);
    }

    @Test
    public void getAll() {
        ResponseEvent<List<City>> cityList = cityManager.getAll();
        assertNotNull(cityList);
        assertEquals(1, cityList.getData().size());
        assertEquals("City", cityList.getData().get(0).getDescription());
    }

    @Test
    public void getAllException() {
        when(cityRespository.findAll()).thenThrow(new NullPointerException());
        ResponseEvent<List<City>> cityList = cityManager.getAll();
        assertNotNull(cityList);
        assertEquals(ResponseCode.APPLICATION_ERROR, cityList.getCode());
    }

    @Test
    public void save() {
        City citySave = new City();
        citySave.setId(0);
        citySave.setDescription("City");
        ResponseEvent<City> city = cityManager.save(citySave);
        assertNotNull(city);
        assertEquals(0,city.getData().getId().intValue());
        assertEquals("City", city.getData().getDescription());
    }

    @Test
    public void saveException() {
        City citySave = new City();
        citySave.setId(1);
        ResponseEvent<City> city = cityManager.save(citySave);
        assertNotNull(city);
        assertEquals(ResponseCode.APPLICATION_ERROR, city.getCode());
    }

    @Test
    public void delete() {
        ResponseEvent<Boolean> city = cityManager.delete(new Integer(0));
        assertNotNull(city);
        assertEquals(ResponseCode.OK,city.getCode());
    }

    @Test
    public void deleteException() {
        ResponseEvent<Boolean> city = cityManager.delete(1);
        assertNotNull(city);
        assertEquals(ResponseCode.APPLICATION_ERROR, city.getCode());
    }

}