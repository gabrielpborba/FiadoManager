package com.fiadomanager.models.dto.clients;

import lombok.Data;

@Data
public class NewClientRequestDTO {

    private Long idClient;
    private String name;
    private String cellphone;

}
