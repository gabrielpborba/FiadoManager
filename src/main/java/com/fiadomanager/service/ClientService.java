package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ClientRepository;
import com.fiadomanager.models.domain.Client;
import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public boolean newClient(NewClientRequestDTO newClientRequestDTO) throws Exception {
        try {
            Client findClient = clientRepository.findByCellphone(newClientRequestDTO.getCellphone());
            if (null == findClient) {
                Client client = new Client();
                client.setName(newClientRequestDTO.getName());
                client.setCellphone(newClientRequestDTO.getCellphone());
                clientRepository.saveAndFlush(client);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao salvar novo Client");
        }

    }

    public ClientResponseDTO getClients() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        List<Client> clients = clientRepository.findAll();
        if (!clients.isEmpty()) {
            clientResponseDTO.setClients(clients);
            return clientResponseDTO;
        } else {
            return null;
        }
    }
}
