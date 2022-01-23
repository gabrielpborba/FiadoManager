package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_PRODUCT")
    @SequenceGenerator(
            name = "SQ_PRODUCT",
            sequenceName = "SQ_PRODUCT",
            allocationSize = 1)
    private Long id;

    private String description;

    private Double value;

}
