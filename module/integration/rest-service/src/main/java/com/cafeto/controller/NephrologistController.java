package com.cafeto.controller;

import com.cafeto.util.ResponseEntityUtility;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistManager;
import com.cafeto.core.application.entity.Nephrologist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/nephrologist")
public class NephrologistController {

    @Autowired
    NephrologistManager nephrologistManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<Nephrologist>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(nephrologistManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<Nephrologist>> save(@RequestBody Nephrologist nephrologist) {
        return ResponseEntityUtility.buildHttpResponse(nephrologistManager.save(nephrologist));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer nephrologist) {
        return ResponseEntityUtility.buildHttpResponse(nephrologistManager.delete(nephrologist));
    }

}
