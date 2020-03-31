package com.cafeto.core.application.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="CLINIC", schema = "public")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String administrator;

    private String email;

    private String director;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "clinic_type_id")
    private ClinicType clinicType;

}
