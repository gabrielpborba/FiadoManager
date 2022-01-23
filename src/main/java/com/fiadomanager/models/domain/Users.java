package com.fiadomanager.models.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity
@Data
@Document(collection = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_USERS")
    @SequenceGenerator(
            name = "SQ_USERS",
            sequenceName = "SQ_USERS",
            allocationSize = 1)
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;


}
