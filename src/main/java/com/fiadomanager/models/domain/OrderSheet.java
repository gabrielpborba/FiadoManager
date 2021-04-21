package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="ORDERSHEET")
public class OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_USERS")
    @SequenceGenerator(
            name = "SQ_ORDERSHEET",
            sequenceName = "SQ_ORDERSHEET",
            allocationSize = 1)
    @Column(name = "ID_ORDERSHEET")
    private Long id;

    @Column(name = "DATE_CREATE")
    private LocalDate dateCreate;

    @Column(name = "DATE_PAYMENT")
    private LocalDate datePayment;

    @ManyToMany
    @JoinTable(name="ORDERSHEET_PRODUCT", joinColumns=
            {@JoinColumn(name="ID_ORDERSHEET")}, inverseJoinColumns=
            {@JoinColumn(name="ID_PRODUCT")})
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

}
