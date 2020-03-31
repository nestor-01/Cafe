package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.Clinic;

import java.util.List;

public interface ClinicManager {

    ResponseEvent<List<Clinic>> getAll();

    ResponseEvent<Clinic> save(Clinic clinic);

    ResponseEvent<Boolean> delete(Integer clinic);

}
