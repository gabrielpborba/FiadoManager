package com.fiadomanager.service;

import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;

public interface ClientService {

    boolean newClient(NewClientRequestDTO newClientRequestDTO) throws FiadoManagerCustomException;

    ClientResponseDTO getClients() throws FiadoManagerCustomException;

    Boolean disableClient(Long idClient) throws FiadoManagerCustomException;
}
