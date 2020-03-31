package com.cafeto.core.application.repository;

import com.cafeto.core.application.entity.Nephrologist;
import com.cafeto.core.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NephrologistRespository extends JpaRepository<Nephrologist, Integer> {

}
