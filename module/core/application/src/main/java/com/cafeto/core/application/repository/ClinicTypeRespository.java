package com.cafeto.core.application.repository;

import com.cafeto.core.application.entity.ClinicType;
import com.cafeto.core.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClinicTypeRespository extends JpaRepository<ClinicType, Integer> {

}
