package com.cafeto.core.application.repository;

import com.cafeto.core.application.entity.City;
import com.cafeto.core.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRespository extends JpaRepository<City, Integer> {

}
