package com.example.demo.Service;

import com.example.demo.Dao.ClientDao;
import com.example.demo.Dao.ClientDataAccessService;
import com.example.demo.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends ClientDataAccessService {
    ClientRepository clientRepository;

    @Autowired
    private ClientDataAccessService clientDataAccessService;

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Optional findById(int id) {
        return Optional.empty();
    }


    @Override
    public void remove(int id) {

    }

    public List<Client> fetchGetClientProcedure(){
        return clientRepository.fetchGetClientProcedure();
    }
}
