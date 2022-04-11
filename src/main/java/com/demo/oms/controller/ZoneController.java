package com.demo.oms.controller;

import com.demo.oms.dto.ZoneDTO;
import com.demo.oms.entity.Zone;
import com.demo.oms.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zone")
@CrossOrigin
public class ZoneController {

    @Autowired
    private ZoneService zoneService;



    public ZoneController(ZoneService zoneService) {

        this.zoneService = zoneService;

    }

    @PostMapping("/addZone")
    public ResponseEntity<?> saveZone(@RequestBody ZoneDTO zone) {
        zoneService.addZone(zone);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/getAllZone")
    public ResponseEntity<List<Zone>> getAllZones() {

        List<Zone> zoneList = zoneService.getAllZone();
        return new ResponseEntity<>(zoneList, HttpStatus.OK);
    }

    @PostMapping("/deleteZone")
    public ResponseEntity<?> DeleteZone(@RequestBody int id) {

        zoneService.deleteZone(id);
        return new ResponseEntity<>(HttpStatus.OK);}

    @PutMapping("/ModifyZone")
    public ResponseEntity<?> ModifyZone( @RequestBody ZoneDTO zone) {

        zoneService.UpdateZone(zone);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/GetZone/{id}")
    public ResponseEntity<Zone> GetZone(  @PathVariable int id) {

        Zone zone= zoneService.getZone(id);
        return new ResponseEntity<>(zone, HttpStatus.OK);
    }

    @GetMapping("/GetZoneCode/{id}")
    public ResponseEntity<Zone> GetZoneCode(  @PathVariable int id) {

        Zone zone= zoneService.getZoneCode(id);
        return new ResponseEntity<>(zone, HttpStatus.OK);
    }



}
