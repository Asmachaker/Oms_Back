package com.demo.oms.controller;

import com.demo.oms.entity.Taille;
import com.demo.oms.entity.Tarif;
import com.demo.oms.service.TailleService;
import com.demo.oms.service.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taille")
@CrossOrigin
public class TailleController {

    @Autowired
    private TailleService tailleService;



    public TailleController(TailleService tailleService) {

        this.tailleService = tailleService;

    }
    @PostMapping("/addTaille")
    public ResponseEntity<?> saveTaille(@RequestBody Taille taille) {
        tailleService.addTaille(taille);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/getAllTaille")
    public ResponseEntity<List<Taille>> getAllTailles() {

        List<Taille> tailleList = tailleService.getAllTaille();
        return new ResponseEntity<>(tailleList, HttpStatus.OK);
    }

    @PostMapping("/deleteTaille")
    public ResponseEntity<?> DeleteTaille(@RequestBody String id) {

        tailleService.deleteTaille(id);
        return new ResponseEntity<>(HttpStatus.OK);}

    @PutMapping("/ModifyTaille")
    public ResponseEntity<?> ModifyTaille( @RequestBody Taille taille) {

        tailleService.UpdateTaille(taille);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/GetTaille/{id}")
    public ResponseEntity<Taille> GetTaille(  @PathVariable String id) {

        Taille taille= tailleService.getTaille(id);
        return new ResponseEntity<>(taille, HttpStatus.OK);
    }




}

