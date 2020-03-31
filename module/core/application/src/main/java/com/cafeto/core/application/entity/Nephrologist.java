package com.cafeto.core.application.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="NEPHROLOGIST", schema = "public")
public class Nephrologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "nephrologist_type_id")
    private NephrologistType nephrologistType;

    private String email;

    private Boolean active;

}
