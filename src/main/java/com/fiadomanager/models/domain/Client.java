package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_CLIENT")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CELLPHONE")
    private String cellphone;

}
