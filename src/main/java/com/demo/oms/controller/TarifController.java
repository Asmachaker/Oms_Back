package com.demo.oms.controller;


import com.demo.oms.dto.TarifDTO;
import com.demo.oms.entity.Tarif;
import com.demo.oms.service.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarif")
@CrossOrigin
public class TarifController {

    @Autowired
    private TarifService tarifService;



    public TarifController(TarifService tarifService) {

        this.tarifService = tarifService;

    }
    @PostMapping("/addTarif")
    public ResponseEntity<?> saveTarif(@RequestBody Tarif tarif) {
        tarifService.addTarif(tarif);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/getAllTarif")
    public ResponseEntity<List<Tarif>> getAllTarifs() {

        List<Tarif> tarifList = tarifService.getAllTarif();
        return new ResponseEntity<>(tarifList, HttpStatus.OK);
    }

    @PostMapping("/deleteTarif")
    public ResponseEntity<?> DeleteTarif(@RequestBody int id) {

        tarifService.deleteTarif(id);
        return new ResponseEntity<>(HttpStatus.OK);}

    @PutMapping("/ModifyTarif")
    public ResponseEntity<?> ModifyTarif( @RequestBody TarifDTO tarif) {

        tarifService.UpdateTarif(tarif);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/GetTarif/{id}")
    public ResponseEntity<Tarif> GetTarif(  @PathVariable int id) {

        Tarif tarif= tarifService.getTarif(id);
        return new ResponseEntity<>(tarif, HttpStatus.OK);
    }


    @GetMapping("/GetTarif/{idZone}/{idTaille}/{idShift}")
    public ResponseEntity<Tarif> GetTarifBy(  @PathVariable("idZone") int idZ, @PathVariable("idTaille") String idT,@PathVariable("idShift") String idS) {

        Tarif tarif= tarifService.getTarifByAtt(idZ, idT, idS);
        return new ResponseEntity<>(tarif, HttpStatus.OK);
    }




}
