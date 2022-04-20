package com.demo.oms.controller;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
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

    @PostMapping("/addbordereau")
    public ResponseEntity<?> savebordereau() {
        bordereauService.generateBordereau();

        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
