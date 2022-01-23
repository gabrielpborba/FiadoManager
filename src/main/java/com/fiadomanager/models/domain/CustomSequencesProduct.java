package com.fiadomanager.models.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "customSequences_product")
@Data
public class CustomSequencesProduct {


    @Id
    private String id;
    private Long seq;

}