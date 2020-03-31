package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ZoneManager;
import com.cafeto.core.application.entity.Zone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/zone")
public class ZoneController {

    @Autowired
    ZoneManager zoneManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<Zone>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(zoneManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<Zone>> save(@RequestBody Zone zone) {
        return ResponseEntityUtility.buildHttpResponse(zoneManager.save(zone));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer zone) {
        return ResponseEntityUtility.buildHttpResponse(zoneManager.delete(zone));
    }

}
