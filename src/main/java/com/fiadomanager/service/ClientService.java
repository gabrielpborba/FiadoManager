package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ClientRepository;
import com.fiadomanager.models.domain.Client;
import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderSheetService orderSheetService;


    public boolean newClient(NewClientRequestDTO newClientRequestDTO) throws Exception {
        Client findClient = clientRepository.findByCellphone(newClientRequestDTO.getCellphone());
        if (null == findClient) {
            Client client = new Client();
            client.setName(newClientRequestDTO.getName());
            client.setCellphone(newClientRequestDTO.getCellphone());
            client.setStatus(1);
            clientRepository.save(client);
            return true;
        } else {
            Client updateClient = new Client();
            updateClient.setId(findClient.getId());
            updateClient.setName(newClientRequestDTO.getName());
            updateClient.setCellphone(newClientRequestDTO.getCellphone());
            updateClient.setStatus(1);
            clientRepository.save(updateClient);
            return true;
        }
    }

    public ClientResponseDTO getClients() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        List<Client> clients = clientRepository.findByStatus(1);
        if (!clients.isEmpty()) {
            clientResponseDTO.setClients(clients);
            return clientResponseDTO;
        } else {
            return null;
        }
    }

    public Boolean disableClient(Long idClient) {

        Optional<Client> client = clientRepository.findById(idClient);

        if (null != client.get()) {
            Client updateClient = new Client();
            updateClient.setId(client.get().getId());
            updateClient.setCellphone(client.get().getCellphone());
            updateClient.setName(client.get().getName());
            updateClient.setStatus(0);
            clientRepository.save(updateClient);
            return true;
        } else {
            return false;
        }

    }
}
