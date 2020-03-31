package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.CityManager;
import com.cafeto.core.application.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/city")
public class CityController {

    @Autowired
    CityManager cityManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<City>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(cityManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<City>> save(@RequestBody City city) {
        return ResponseEntityUtility.buildHttpResponse(cityManager.save(city));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer city) {
        return ResponseEntityUtility.buildHttpResponse(cityManager.delete(city));
    }

}
