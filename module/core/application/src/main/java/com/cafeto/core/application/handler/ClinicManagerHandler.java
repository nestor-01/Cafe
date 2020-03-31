package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ClinicManager;
import com.cafeto.core.api.manager.ClinicManager;
import com.cafeto.core.application.entity.Clinic;
import com.cafeto.core.application.repository.ClinicRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClinicManagerHandler implements ClinicManager {

    @Autowired
    ClinicRespository clinicRespository;

    @Override
    public ResponseEvent<List<Clinic>> getAll() {
        try {
            return new ResponseEvent<List<Clinic>>().ok(Constants.RESPONSE_SUCESS, clinicRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<Clinic>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<Clinic> save(Clinic clinic) {
        try {
            return new ResponseEvent<Clinic>().ok(Constants.RESPONSE_SUCESS, clinicRespository.save(clinic));
        } catch (Exception e) {
            log.error("CLINIC ERROR", e);
            return new ResponseEvent<Clinic>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer clinic) {
        try {
            clinicRespository.deleteById(clinic);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("CLINIC ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}