package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.UserManager;
import com.cafeto.core.application.entity.User;
import com.cafeto.core.application.repository.UserRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserManagerHandler implements UserManager {

    @Autowired
    UserRespository userRespository;

    @Override
    public ResponseEvent<List<User>> getAll() {
        try {
            return new ResponseEvent<List<User>>().ok(Constants.RESPONSE_SUCESS, userRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<User>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<User> save(User user) {
        try {
            return new ResponseEvent<User>().ok(Constants.RESPONSE_SUCESS, userRespository.save(user));
        } catch (Exception e) {
            log.error("USER ERROR", e);
            return new ResponseEvent<User>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer user) {
        try {
            userRespository.deleteById(user);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("USER ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}