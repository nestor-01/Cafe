package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.Nephrologist;

import java.util.List;

public interface NephrologistManager {

    ResponseEvent<List<Nephrologist>> getAll();

    ResponseEvent<Nephrologist> save(Nephrologist nephrologist);

    ResponseEvent<Boolean> delete(Integer nephrologist);

}
