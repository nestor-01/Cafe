package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.CityManager;
import com.cafeto.core.api.manager.CityManager;
import com.cafeto.core.application.entity.City;
import com.cafeto.core.application.repository.CityRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CityManagerHandler implements CityManager {

    @Autowired
    CityRespository cityRespository;

    @Override
    public ResponseEvent<List<City>> getAll() {
        try {
            return new ResponseEvent<List<City>>().ok(Constants.RESPONSE_SUCESS, cityRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<City>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<City> save(City city) {
        try {
            return new ResponseEvent<City>().ok(Constants.RESPONSE_SUCESS, cityRespository.save(city));
        } catch (Exception e) {
            log.error("CITY ERROR", e);
            return new ResponseEvent<City>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer city) {
        try {
            cityRespository.deleteById(city);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("CITY ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}