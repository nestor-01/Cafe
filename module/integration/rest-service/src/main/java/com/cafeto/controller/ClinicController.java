package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ClinicManager;
import com.cafeto.core.application.entity.Clinic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/clinic")
public class ClinicController {

    @Autowired
    ClinicManager clinicManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<Clinic>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(clinicManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<Clinic>> save(@RequestBody Clinic clinic) {
        return ResponseEntityUtility.buildHttpResponse(clinicManager.save(clinic));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer clinic) {
        return ResponseEntityUtility.buildHttpResponse(clinicManager.delete(clinic));
    }

}
