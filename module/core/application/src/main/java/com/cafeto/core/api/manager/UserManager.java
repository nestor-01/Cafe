package com.cafeto.core.api.manager;


import com.cafeto.core.api.events.ResponseEvent;
import com.cafeto.core.application.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserManager {

    ResponseEvent<List<User>> getAll();

    ResponseEvent<User> save(User user);

    ResponseEvent<Boolean> delete(Integer user);

}
