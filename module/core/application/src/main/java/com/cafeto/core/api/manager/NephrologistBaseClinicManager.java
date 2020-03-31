package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.NephrologistBaseClinic;

import java.util.List;

public interface NephrologistBaseClinicManager {

    ResponseEvent<List<NephrologistBaseClinic>> getAll();

    ResponseEvent<NephrologistBaseClinic> save(NephrologistBaseClinic nephrologistBaseClinic);

    ResponseEvent<Boolean> delete(Integer nephrologistBaseClinic);

}
