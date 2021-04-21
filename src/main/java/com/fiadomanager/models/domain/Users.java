package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="USERS")
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

}
