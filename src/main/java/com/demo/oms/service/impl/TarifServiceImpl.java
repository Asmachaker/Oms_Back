package com.demo.oms.service.impl;

import com.demo.oms.dto.TarifDTO;
import com.demo.oms.entity.Shift;
import com.demo.oms.entity.Taille;
import com.demo.oms.entity.Tarif;
import com.demo.oms.entity.Zone;
import com.demo.oms.repository.ShiftRepository;
import com.demo.oms.repository.TailleRepository;
import com.demo.oms.repository.TarifRepository;
import com.demo.oms.repository.ZoneRepository;
import com.demo.oms.service.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifServiceImpl implements TarifService {

    @Autowired
    private TarifRepository tarifRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private TailleRepository tailleRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public void addTarif(Tarif tarif) {
        tarifRepository.save(tarif);
    }

    @Override
    public List<Tarif> getAllTarif() {
        return tarifRepository.findAll();
    }


    @Override
    public Tarif getTarif(Integer id) {

        return tarifRepository.findById(id).get();
    }

    @Override
    public void UpdateTarif(TarifDTO tarifDTO) {
        Shift shift = shiftRepository.getShiftByName(tarifDTO.getShift());
        Zone zone = zoneRepository.getZoneByName(tarifDTO.getZone());
        Taille taille= tailleRepository.getTailleByName(tarifDTO.getTaille());
        Tarif tarif = new Tarif(tarifDTO.getId(),tarifDTO.getName(),tarifDTO.getPrice(),shift,zone,taille);
        tarifRepository.save(tarif);

    }

    @Override
    public Tarif getTarifByAtt(int idZ, String idT, String idS)
    {  Zone zone = zoneRepository.findById(idZ).get();
        Taille taille= tailleRepository.findById(idT).get();
        Shift shift = shiftRepository.getShiftByName(idS);

        return tarifRepository.getTarifByAtt(zone,taille,shift);}

    @Override
    public void deleteTarif(Integer id) {

         tarifRepository.deleteById(id);
    }
}
