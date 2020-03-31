package com.cafeto.core.application.repository;

import com.cafeto.core.application.entity.Clinic;
import com.cafeto.core.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClinicRespository extends JpaRepository<Clinic, Integer> {

}
