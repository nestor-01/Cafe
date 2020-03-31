package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ClinicTypeManager;
import com.cafeto.core.application.entity.ClinicType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/clinicType")
public class ClinicTypeController {

    @Autowired
    ClinicTypeManager clinicTypeManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<ClinicType>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(clinicTypeManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<ClinicType>> save(@RequestBody ClinicType clinicType) {
        log.info("Clinic type -> " + clinicType);
        return ResponseEntityUtility.buildHttpResponse(clinicTypeManager.save(clinicType));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer clinicType) {
        return ResponseEntityUtility.buildHttpResponse(clinicTypeManager.delete(clinicType));
    }

}
