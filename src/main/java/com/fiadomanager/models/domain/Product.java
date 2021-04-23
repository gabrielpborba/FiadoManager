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
    @Column(name = "ID_PRODUCT")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private Long value;

}
