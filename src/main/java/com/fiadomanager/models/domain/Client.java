package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_CLIENT")
    @SequenceGenerator(
            name = "SQ_CLIENT",
            sequenceName = "SQ_CLIENT",
            allocationSize = 1)
    @Column(name = "ID_CLIENT")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "STATUS")
    private Integer status;


}
