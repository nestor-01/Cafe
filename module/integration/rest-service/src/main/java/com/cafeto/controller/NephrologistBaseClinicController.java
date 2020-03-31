package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistBaseClinicManager;
import com.cafeto.core.application.entity.NephrologistBaseClinic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/nephrologistBaseClinic")
public class NephrologistBaseClinicController {

    @Autowired
    NephrologistBaseClinicManager nephrologistBaseClinicManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<NephrologistBaseClinic>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(nephrologistBaseClinicManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<NephrologistBaseClinic>> save(@RequestBody NephrologistBaseClinic nephrologistBaseClinic) {
        return ResponseEntityUtility.buildHttpResponse(nephrologistBaseClinicManager.save(nephrologistBaseClinic));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer nephrologistBaseClinic) {
        return ResponseEntityUtility.buildHttpResponse(nephrologistBaseClinicManager.delete(nephrologistBaseClinic));
    }

}
