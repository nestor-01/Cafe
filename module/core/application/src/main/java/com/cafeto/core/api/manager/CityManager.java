package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.City;

import java.util.List;

public interface CityManager {

    ResponseEvent<List<City>> getAll();

    ResponseEvent<City> save(City city);

    ResponseEvent<Boolean> delete(Integer city);

}
