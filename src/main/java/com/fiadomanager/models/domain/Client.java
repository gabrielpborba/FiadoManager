package com.fiadomanager.models.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@Entity
@Document(collection = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_CLIENT")
    @SequenceGenerator(
            name = "SQ_CLIENT",
            sequenceName = "SQ_CLIENT",
            allocationSize = 1)
    private Long id;

    private String name;

    private String cellphone;

    private Integer status;


}
