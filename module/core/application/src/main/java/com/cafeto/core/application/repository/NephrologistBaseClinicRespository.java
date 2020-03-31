package com.cafeto.core.application.repository;

import com.cafeto.core.application.entity.NephrologistBaseClinic;
import com.cafeto.core.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NephrologistBaseClinicRespository extends JpaRepository<NephrologistBaseClinic, Integer> {

}