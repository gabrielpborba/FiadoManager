package com.fiadomanager.models.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "customSequences_client")
@Data
public class CustomSequencesClient {

    @Id
    private String id;
    private Long seq;

}