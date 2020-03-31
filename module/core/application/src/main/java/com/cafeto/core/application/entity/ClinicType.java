package com.cafeto.core.application.entity;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="CLINIC_TYPE", schema = "public")
public class ClinicType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
