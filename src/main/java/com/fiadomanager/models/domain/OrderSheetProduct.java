package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDERSHEET_PRODUCT")
public class OrderSheetProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_ORDERSHEET_PRODUCT")
    @SequenceGenerator(
            name = "SQ_ORDERSHEET_PRODUCT",
            sequenceName = "SQ_ORDERSHEET_PRODUCT",
            allocationSize = 1)
    @Column(name = "ID_ORDERSHEET_PRODUCT")
    private Long id;

    @Column(name = "ID_ORDERSHEET")
    private Long idOrderSheet;

    @Column(name = "ID_PRODUCT")
    private Long idProduct;

    @Column(name = "QUANTITY")
    private Long quantity;


}
