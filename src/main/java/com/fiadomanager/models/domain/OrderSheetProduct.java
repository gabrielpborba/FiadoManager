package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orderSheetProduct")
public class OrderSheetProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_ORDERSHEET_PRODUCT")
    @SequenceGenerator(
            name = "SQ_ORDERSHEET_PRODUCT",
            sequenceName = "SQ_ORDERSHEET_PRODUCT",
            allocationSize = 1)
    private Long id;

    private Long idOrderSheet;

    private Long idProduct;

    private Long quantity;


}
