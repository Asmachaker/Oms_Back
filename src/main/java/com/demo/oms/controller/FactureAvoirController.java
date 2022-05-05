package com.demo.oms.controller;


import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Facture;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.entity.Tarif;
import com.demo.oms.service.FactureAvoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/factureAvoir")
@CrossOrigin
public class FactureAvoirController {

    @Autowired
    private FactureAvoirService factureAvoirService;


    public FactureAvoirController(FactureAvoirService factureAvoirService) {

        this.factureAvoirService = factureAvoirService;

    }

    @PostMapping("/addFactureAvoir")
    public ResponseEntity<?> saveFactureAvoir(@RequestBody FactureAvoir factureAvoir) {
        factureAvoirService.addFactureAvoir(factureAvoir);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/EnableFactureAvoir/{id}")
    public ResponseEntity<?> EnableFactureAvoir(@PathVariable Long id) {

        factureAvoirService.EnableFactureAvoir(id);
        return new ResponseEntity<>(HttpStatus.OK);}

    @GetMapping("/getAllFactureAvoir")
    public ResponseEntity<List<FactureAvoir>> getAllFactureAvoirs() {

        List<FactureAvoir> factureAvoirList = factureAvoirService.getAllFactureAvoir();
        return new ResponseEntity<>(factureAvoirList, HttpStatus.OK);
    }

    @GetMapping("/GetFactureAvoir/{id}")
    public ResponseEntity<FactureAvoir> GetFactureAvoir(@PathVariable Long id) {

        FactureAvoir factureAvoir= factureAvoirService.getFactureAvoir(id);
        return new ResponseEntity<>(factureAvoir, HttpStatus.OK);
    }
    @PostMapping("/generateFactureAvoir/{id}")
    public ResponseEntity<Map<Integer, Integer>> Generatefacture(@RequestBody List<Booking> list, @PathVariable Long id ) {

        Map<Integer, Integer> tarifMap = factureAvoirService.generateFactureAvoir(list,id);

        return new ResponseEntity<Map<Integer, Integer>>(tarifMap,HttpStatus.OK);}


}
