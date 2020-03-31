package com.cafeto.core.application.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="NEPHROLOGIST_BASE_CLINIC", schema = "public")
public class NephrologistBaseClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nephrologist_id")
    private Nephrologist nephrologist;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private Double salary;

    private Integer dedicationHours;
}
