package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ClinicTypeManager;
import com.cafeto.core.api.manager.ClinicTypeManager;
import com.cafeto.core.application.entity.ClinicType;
import com.cafeto.core.application.repository.ClinicTypeRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClinicTypeManagerHandler implements ClinicTypeManager {

    @Autowired
    ClinicTypeRespository clinicTypeRespository;

    @Override
    public ResponseEvent<List<ClinicType>> getAll() {
        try {
            return new ResponseEvent<List<ClinicType>>().ok(Constants.RESPONSE_SUCESS, clinicTypeRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<ClinicType>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<ClinicType> save(ClinicType clinicType) {
        try {
            return new ResponseEvent<ClinicType>().ok(Constants.RESPONSE_SUCESS, clinicTypeRespository.save(clinicType));
        } catch (Exception e) {
            log.error("CLINIC TYPE ERROR", e);
            return new ResponseEvent<ClinicType>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer clinicType) {
        try {
            clinicTypeRespository.deleteById(clinicType);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("CLINIC TYPE ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}