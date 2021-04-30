package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ClientRepository;
import com.fiadomanager.models.domain.Client;
import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderSheetService orderSheetService;


    public boolean newClient(NewClientRequestDTO newClientRequestDTO) throws FiadoManagerCustomException {
        try {
            Client findClient = clientRepository.findByName(newClientRequestDTO.getName());

            if (null != findClient) {
                throw new FiadoManagerCustomException(HttpStatus.CONFLICT, "Já existe um cliente com esse nome");
            }

            if (Strings.isNullOrEmpty(String.valueOf(newClientRequestDTO.getIdClient()))) {
                Client client = new Client();
                client.setName(newClientRequestDTO.getName());
                client.setCellphone(newClientRequestDTO.getCellphone());
                client.setStatus(1);
                clientRepository.save(client);
                return true;
            } else {
                Client client = new Client();
                client.setId(newClientRequestDTO.getIdClient());
                client.setName(newClientRequestDTO.getName());
                client.setCellphone(newClientRequestDTO.getCellphone());
                client.setStatus(1);
                clientRepository.save(client);
                return true;
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public ClientResponseDTO getClients() throws FiadoManagerCustomException {

        try {
            ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
            List<Client> clients = clientRepository.findByStatus(1);
            if (!clients.isEmpty()) {
                clientResponseDTO.setClients(clients);
                return clientResponseDTO;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Nenhum cliente cadastrado");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean disableClient(Long idClient) throws FiadoManagerCustomException {

        try {

            Optional<Client> client = clientRepository.findById(idClient);

            if (!client.isEmpty()) {
                Client updateClient = new Client();
                updateClient.setId(client.get().getId());
                updateClient.setCellphone(client.get().getCellphone());
                updateClient.setName(client.get().getName());
                updateClient.setStatus(0);
                clientRepository.save(updateClient);
                return true;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
            }

        } catch (Exception e) {
            throw e;
        }

    }
}
