package com.fiadomanager.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_USERS")
    @SequenceGenerator(
            name = "SQ_USERS",
            sequenceName = "SQ_USERS",
            allocationSize = 1)
    private Long id;

    private String username;

    private String name;

    private String password;


}
