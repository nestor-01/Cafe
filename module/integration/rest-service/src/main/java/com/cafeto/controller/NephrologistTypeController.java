package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistTypeManager;
import com.cafeto.core.application.entity.NephrologistType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/nephrologistType")
public class NephrologistTypeController {

    @Autowired
    NephrologistTypeManager nephrologistTypeManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<NephrologistType>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(nephrologistTypeManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<NephrologistType>> save(@RequestBody NephrologistType nephrologistType) {
        return ResponseEntityUtility.buildHttpResponse(nephrologistTypeManager.save(nephrologistType));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer nephrologistType) {
        return ResponseEntityUtility.buildHttpResponse(nephrologistTypeManager.delete(nephrologistType));
    }

}
