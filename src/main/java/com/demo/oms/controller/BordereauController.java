package com.demo.oms.controller;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.service.BordereauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bordereau")
@CrossOrigin
public class BordereauController {


    @Autowired
    private BordereauService bordereauService;


    @GetMapping("/getAllbordereaus")
    public ResponseEntity<List<Bordereau>> getAllBordereaus() {

        List<Bordereau> BordereauList = bordereauService.getAllBordereaus();
        return new ResponseEntity<>(BordereauList, HttpStatus.OK);
    }

    @GetMapping("/addbordereau")
    public ResponseEntity<Bordereau> savebordereau() {
       Bordereau bordereau= bordereauService.generateBordereau();

        return new ResponseEntity<Bordereau>( bordereau,HttpStatus.CREATED);
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
