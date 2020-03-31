package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.NephrologistType;

import java.util.List;

public interface NephrologistTypeManager {

    ResponseEvent<List<NephrologistType>> getAll();

    ResponseEvent<NephrologistType> save(NephrologistType nephrologistType);

    ResponseEvent<Boolean> delete(Integer nephrologistType);

}
