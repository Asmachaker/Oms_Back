package com.demo.oms.service.impl;

import com.demo.oms.dto.ZoneDTO;
import com.demo.oms.entity.CodePostal;
import com.demo.oms.entity.Zone;
import com.demo.oms.repository.CodePostalRepository;
import com.demo.oms.repository.ZoneRepository;
import com.demo.oms.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private CodePostalRepository codeRepository;

    @Override
    public void addZone(ZoneDTO zone) {

        CodePostal code = codeRepository.getCodeByName(zone.getCodePostal());
        System.out.print(code);
        Zone  zones =new Zone(zone.getId(), zone.getName(),code);
        zoneRepository.save(zones);
    }

    @Override
    public List<Zone> getAllZone() {
        return zoneRepository.findAll();
    }


    @Override
    public Zone getZone(Integer id) {

        return zoneRepository.findById(id).get();
    }

    @Override
    public Zone getZoneCode(Integer id) {
        CodePostal code = codeRepository.findById(id).get();
        return zoneRepository.getZoneBycode(code);
    }

    @Override
    public void UpdateZone(ZoneDTO zone) {
        CodePostal code = codeRepository.getCodeByName(zone.getCodePostal());
        System.out.print(code);
        Zone  zones =new Zone(zone.getId(), zone.getName(),code);
        zoneRepository.save(zones);
    }

    @Override
    public void deleteZone(Integer id) {

        zoneRepository.deleteById(id);
    }
}

