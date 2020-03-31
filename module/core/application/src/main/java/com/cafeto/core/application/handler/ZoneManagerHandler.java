package com.cafeto.core.application.handler;

import com.cafeto.core.api.constants.Constants;
import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.api.manager.ZoneManager;
import com.cafeto.core.api.manager.ZoneManager;
import com.cafeto.core.application.entity.Zone;
import com.cafeto.core.application.repository.ZoneRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ZoneManagerHandler implements ZoneManager {

    @Autowired
    ZoneRespository zoneRespository;

    @Override
    public ResponseEvent<List<Zone>> getAll() {
        try {
            return new ResponseEvent<List<Zone>>().ok(Constants.RESPONSE_SUCESS, zoneRespository.findAll());
        } catch (Exception e) {
            return new ResponseEvent<List<Zone>>().applicationError(e.getMessage());
        }
    }

    @Override
    public ResponseEvent<Zone> save(Zone zone) {
        try {
            return new ResponseEvent<Zone>().ok(Constants.RESPONSE_SUCESS, zoneRespository.save(zone));
        } catch (Exception e) {
            log.error("ZONE ERROR", e);
            return new ResponseEvent<Zone>().applicationError("Application error");
        }
    }

    @Override
    public ResponseEvent<Boolean> delete(Integer zone) {
        try {
            zoneRespository.deleteById(zone);
            return new ResponseEvent<Boolean>().ok(Constants.RESPONSE_SUCESS);
        } catch (Exception e) {
            log.error("ZONE ERROR", e);
            return new ResponseEvent<Boolean>().applicationError("Application error");
        }
    }

}