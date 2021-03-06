package com.demo.oms.controller;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.entity.Facture;
import com.demo.oms.repository.BordereauRepository;
import com.demo.oms.service.BordereauService;
import com.demo.oms.service.FactureService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bordereau")
@CrossOrigin
public class BordereauController {


    @Autowired
    private BordereauService bordereauService;

    @Autowired
    private FactureService factureService;

    @Autowired
    private BordereauRepository bordereauRepo;


    @GetMapping("/getAllbordereaus")
    public ResponseEntity<List<Bordereau>> getAllBordereaus() {

        List<Bordereau> BordereauList = bordereauService.getAllBordereaus();
        return new ResponseEntity<>(BordereauList, HttpStatus.OK);
    }

    @GetMapping("/addbordereau")
    public ResponseEntity<?> savebordereau() throws JRException, FileNotFoundException {
       List<Bordereau> bordereau= bordereauService.generateBordereau();
       factureService.genererAllFacture(bordereau);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/time")
    public ResponseEntity<List<Bordereau>> savbordereau() {
        Date now = Date.valueOf(LocalDate.now());
        List <Bordereau> bor =bordereauRepo.getBordereauBydate(now);
        return new ResponseEntity<>(bor,HttpStatus.CREATED);
    }

    @PostMapping("/addbordereauClient")
    public ResponseEntity<?> savebordereauClient(@RequestBody Client client) throws JRException, FileNotFoundException {
        Bordereau bordereau= bordereauService.generateBordereauClient(client);
        factureService.exportReport(bordereau);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/GetBordereau/{id}")
    public ResponseEntity<Bordereau> GetBordereau(@PathVariable Long id) {

        Bordereau bordereau= bordereauService.getBordereau(id);
        return new ResponseEntity<>(bordereau, HttpStatus.OK);
    }

    @PostMapping("/deleteBordereau/{id}")
    public ResponseEntity<?> DeleteBordereau(@PathVariable Long id) {

        bordereauService.deleteBordereau(id);
        return new ResponseEntity<>(HttpStatus.OK);}


}
