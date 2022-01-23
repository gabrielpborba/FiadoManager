package com.fiadomanager.models.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Document(collection = "ORDERSHEET")
public class OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_ORDERSHEET")
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

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ORDERSHEET_PRODUCT",
            joinColumns = { @JoinColumn(name = "ID_ORDERSHEET") },
            inverseJoinColumns = { @JoinColumn(name = "ID_PRODUCT") })
    private List<Product> products;


}
