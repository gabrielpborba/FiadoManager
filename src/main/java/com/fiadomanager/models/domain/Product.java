package com.fiadomanager.models.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@Entity
@Document(collection = "PRODUCT")
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
    private Double value;

}
