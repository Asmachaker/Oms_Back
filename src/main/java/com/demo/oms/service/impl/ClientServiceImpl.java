package com.demo.oms.service.impl;


import com.demo.oms.entity.Client;
import com.demo.oms.entity.CodePostal;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client client) {
       clientRepository.save(client);

    }

    @Override
    public void EnableClient(String id) {
        clientRepository.enableClient(id);
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }


    @Override
    public Boolean DisableClient(String id) {
        String email =clientRepository.findById(id).get().getEmail();
        clientRepository.DisableClient(id);
        return true;
    }

    @Override
    public Client getClient(String id) {

        return clientRepository.findById(id).get();
    }

    @Override
    public void UpdateClient(Client client) {

        clientRepository.save(client);

    }
}
