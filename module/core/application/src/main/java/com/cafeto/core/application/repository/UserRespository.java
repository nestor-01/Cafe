package com.cafeto.core.application.repository;

import com.cafeto.core.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Integer> {

}
