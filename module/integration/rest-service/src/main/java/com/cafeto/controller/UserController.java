package com.cafeto.controller;

import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.UserManager;
import com.cafeto.core.application.entity.User;
import com.cafeto.util.ResponseEntityUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services/v1/user")
public class UserController {

    @Autowired
    UserManager userManager;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEvent<List<User>>> getAll() {
        return ResponseEntityUtility.buildHttpResponse(userManager.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEvent<User>> save(@RequestBody User user) {
        return ResponseEntityUtility.buildHttpResponse(userManager.save(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseEvent<Boolean>> delete(@RequestParam("id") Integer user) {
        return ResponseEntityUtility.buildHttpResponse(userManager.delete(user));
    }

}
