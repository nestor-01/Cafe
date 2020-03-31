package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistTypeManager;
import com.cafeto.core.api.manager.NephrologistTypeManager;
import com.cafeto.core.application.entity.NephrologistType;
import com.cafeto.core.application.repository.NephrologistTypeRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NephrologistTypeManagerHandler implements NephrologistTypeManager {

    @Autowired
    NephrologistTypeRespository nephrologistTypeRespository;

    @Override
    public ResponseEvent<List<NephrologistType>> getAll() {
        try {
            return new ResponseEvent<List<NephrologistType>>().ok(Constants.RESPONSE_SUCESS, nephrologistTypeRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<NephrologistType>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<NephrologistType> save(NephrologistType nephrologistType) {
        try {
            return new ResponseEvent<NephrologistType>().ok(Constants.RESPONSE_SUCESS, nephrologistTypeRespository.save(nephrologistType));
        } catch (Exception e) {
            log.error("NephrologistType ERROR", e);
            return new ResponseEvent<NephrologistType>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer nephrologistType) {
        try {
            nephrologistTypeRespository.deleteById(nephrologistType);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("NephrologistType ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}