package com.demo.oms.controller;

import com.demo.oms.entity.Shift;
import com.demo.oms.entity.Tarif;
import com.demo.oms.service.ShiftService;
import com.demo.oms.service.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/shift")
@CrossOrigin
public class ShiftController {

    @Autowired
    private ShiftService shiftService;


    @GetMapping("/getAllShift")
    public ResponseEntity<List<Shift>> getAllShifts() {

        List<Shift> shiftList = shiftService.getAllShift();
        return new ResponseEntity<>(shiftList, HttpStatus.OK);
    }

}
