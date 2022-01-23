package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERSHEET")
public class OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_ORDERSHEET")
    @SequenceGenerator(
            name = "SQ_ORDERSHEET",
            sequenceName = "SQ_ORDERSHEET",
            allocationSize = 1)
    private Long id;

    private LocalDate dateCreate;

    private LocalDate datePayment;

    @ManyToOne
    private Client client;

    private Integer status;

    @ManyToMany
    private List<Product> products;


}
