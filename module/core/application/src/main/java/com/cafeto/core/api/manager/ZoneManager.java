package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.Zone;

import java.util.List;

public interface ZoneManager {

    ResponseEvent<List<Zone>> getAll();

    ResponseEvent<Zone> save(Zone zone);

    ResponseEvent<Boolean> delete(Integer zone);

}
