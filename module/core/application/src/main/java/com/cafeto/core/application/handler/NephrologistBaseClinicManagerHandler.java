package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.NephrologistBaseClinicManager;
import com.cafeto.core.application.entity.NephrologistBaseClinic;
import com.cafeto.core.application.repository.NephrologistBaseClinicRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NephrologistBaseClinicManagerHandler implements NephrologistBaseClinicManager {

    @Autowired
    NephrologistBaseClinicRespository nephrologistBaseClinicRespository;

    @Override
    public ResponseEvent<List<NephrologistBaseClinic>> getAll() {
        try {
            return new ResponseEvent<List<NephrologistBaseClinic>>().ok(Constants.RESPONSE_SUCESS, nephrologistBaseClinicRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<NephrologistBaseClinic>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<NephrologistBaseClinic> save(NephrologistBaseClinic nephrologistBaseClinic) {
        try {
            return new ResponseEvent<NephrologistBaseClinic>().ok(Constants.RESPONSE_SUCESS, nephrologistBaseClinicRespository.save(nephrologistBaseClinic));
        } catch (Exception e) {
            log.error("NephrologistBaseClinic ERROR", e);
            return new ResponseEvent<NephrologistBaseClinic>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer nephrologistBaseClinic) {
        try {
            nephrologistBaseClinicRespository.deleteById(nephrologistBaseClinic);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("NephrologistBaseClinic ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}