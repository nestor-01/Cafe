package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.ClinicType;

import java.util.List;

public interface ClinicTypeManager {

    ResponseEvent<List<ClinicType>> getAll();

    ResponseEvent<ClinicType> save(ClinicType clinicType);

    ResponseEvent<Boolean> delete(Integer clinicType);

}
