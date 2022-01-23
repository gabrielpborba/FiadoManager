package com.fiadomanager.service.impl;

import com.fiadomanager.infrastructure.repository.ClientRepository;
import com.fiadomanager.models.domain.Client;
import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import com.fiadomanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderSheetServiceImpl orderSheetServiceImpl;

    @Autowired
    private NextSequenceService nextSequenceService;


    public boolean newClient(NewClientRequestDTO newClientRequestDTO) throws FiadoManagerCustomException {
        try {
            Client findClient = clientRepository.findByName(StringUtils.capitalize(newClientRequestDTO.getName()));

            if (null != findClient) {
                findClient.setName(StringUtils.capitalize(newClientRequestDTO.getName()));
                findClient.setCellphone(newClientRequestDTO.getCellphone());
                findClient.setStatus(1);
                clientRepository.save(findClient);
                return true;
            }

            if (null == newClientRequestDTO.getIdClient()) {
                Client client = new Client();
                client.setId(nextSequenceService.getNextSequenceClient("customSequences_client"));
                client.setName(StringUtils.capitalize(newClientRequestDTO.getName()));
                client.setCellphone(newClientRequestDTO.getCellphone());
                client.setStatus(1);
                clientRepository.save(client);
                return true;
            } else {
                Client client = new Client();
                client.setId(newClientRequestDTO.getIdClient());
                client.setName(StringUtils.capitalize(newClientRequestDTO.getName()));
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
                clients.sort(Comparator.comparing(Client::getName));
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
