package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistManager;
import com.cafeto.core.api.manager.NephrologistManager;
import com.cafeto.core.application.entity.Nephrologist;
import com.cafeto.core.application.repository.NephrologistRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NephrologistManagerHandler implements NephrologistManager {

    @Autowired
    NephrologistRespository nephrologistRespository;

    @Override
    public ResponseEvent<List<Nephrologist>> getAll() {
        try {
            return new ResponseEvent<List<Nephrologist>>().ok(Constants.RESPONSE_SUCESS, nephrologistRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<Nephrologist>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<Nephrologist> save(Nephrologist nephrologist) {
        try {
            return new ResponseEvent<Nephrologist>().ok(Constants.RESPONSE_SUCESS, nephrologistRespository.save(nephrologist));
        } catch (Exception e) {
            log.error("Nephrologist ERROR", e);
            return new ResponseEvent<Nephrologist>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer nephrologist) {
        try {
            nephrologistRespository.deleteById(nephrologist);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("Nephrologist ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}