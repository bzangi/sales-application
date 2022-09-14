package org.bzangi.service;

import org.bzangi.model.Client;
import org.bzangi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientService( ClientRepository repository ){
        this.repository = repository;
    }

    public void saveClient(Client client){
        validateClient(client);
        this.repository.save(client);
    }

    public void validateClient(Client client){
        // do the validations
    }
}
