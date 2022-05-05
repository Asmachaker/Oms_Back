package com.demo.oms.controller;

import com.demo.oms.entity.Facture;
import com.demo.oms.entity.Taille;
import com.demo.oms.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
@CrossOrigin
public class FactureController {

    @Autowired
    private FactureService factureService;


    public FactureController(FactureService factureService) {

        this.factureService = factureService;

    }

    @PostMapping("/addFacture")
    public ResponseEntity<?> saveFacture(@RequestBody Facture facture) {
        factureService.addFacture(facture);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllFacture")
    public ResponseEntity<List<Facture>> getAllFactures() {

        List<Facture> factureList = factureService.getAllFacture();
        return new ResponseEntity<>(factureList, HttpStatus.OK);
    }

    @GetMapping("/GetFacture/{id}")
    public ResponseEntity<Facture> GetFacture(@PathVariable Long id) {

        Facture facture= factureService.getFacture(id);
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    @PostMapping("/EnableFacture/{id}")
    public ResponseEntity<?> EnableFacture(@PathVariable Long id) {

        factureService.EnableFacture(id);
        return new ResponseEntity<>(HttpStatus.CREATED);}

}
