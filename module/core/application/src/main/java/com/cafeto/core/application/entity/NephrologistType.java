package com.cafeto.core.application.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="NEPHROLOGIST_TYPE", schema = "public")
public class NephrologistType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

}
