package com.fiadomanager.models.dto.clients;

import com.fiadomanager.models.domain.Client;
import lombok.Data;

import java.util.List;

@Data
public class ClientResponseDTO {

    private List<Client> clients;

}
