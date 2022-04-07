package com.demo.oms.service;

import com.demo.oms.entity.Client;
import com.demo.oms.entity.CodePostal;

import java.util.List;

public interface ClientService {

    void addClient(Client clientUser);

    void EnableClient(String id);

    List<Client> getAllClient();


    Boolean DisableClient(String id);

    Client getClient(String id);

    void UpdateClient(Client client);
}
