package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Service.ClientRepository;
import com.example.demo.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {
    ClientService clientService;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RequestMapping("/List")
    @Transactional
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientService.fetchGetClientProcedure();
        return ResponseEntity.ok(clients);
    }
}
