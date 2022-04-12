package com.demo.oms.controller;



import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;
import com.demo.oms.entity.CodePostal;
import com.demo.oms.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;



    public ClientController(ClientService clientService) {

        this.clientService = clientService;

    }
    @PostMapping("/addClient")
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        clientService.addClient(client);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/getAllClients")
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> clientList = clientService.getAllClient();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @GetMapping("/getAllActiveClient")
    public ResponseEntity<List<Client>> getAllBookingbyClient() {

        List<Client> bookingList= clientService.getBookings();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @PostMapping("/EnableClient/{id}")
    public ResponseEntity<?> EnableClient(@PathVariable String id) {

        clientService.EnableClient(id);
        return new ResponseEntity<>(HttpStatus.OK);}

    @PostMapping("/DisableClient/{id}")
    public ResponseEntity<?> DisableClient(@PathVariable String id) {

        clientService.DisableClient(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/ModifyClient")
    public ResponseEntity<?> ModifyClient( @RequestBody Client client) {

        clientService.UpdateClient(client);
        return new ResponseEntity<>(HttpStatus.OK);

    }

        @GetMapping("/GetClient/{id}")
    public ResponseEntity<Client> GetClient(@PathVariable String id) {

        Client client= clientService.getClient(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }



}