package com.demo.oms.service.impl;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.entity.Tarif;
import com.demo.oms.repository.FactureAvoirRepository;
import com.demo.oms.service.BordereauService;
import com.demo.oms.service.FactureAvoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class FactureAvoirServiceImpl implements FactureAvoirService {

    @Autowired
    FactureAvoirRepository factureAvoirRepository;

    @Autowired
    BordereauService bordereauService;


    @Override
    public void addFactureAvoir(FactureAvoir factureAvoir) {
        factureAvoirRepository.save(factureAvoir);
    }

    @Override
    public FactureAvoir getFactureAvoir(Long id) {

        return factureAvoirRepository.findById(id).get();
    }

    @Override
    public List<FactureAvoir> getAllFactureAvoir() {
        return factureAvoirRepository.findAll();
    }

    @Override
    public void EnableFactureAvoir(Long id) {
        factureAvoirRepository.enableFactureAvoir(id);
    }

    @Override
    public Map<Integer, Integer> generateFactureAvoir(List<Booking> bookings, Long id) {
        Bordereau bordereau = bordereauService.getBordereau(id);
        FactureAvoir factureAvoir = new FactureAvoir();
        factureAvoir.setClient(bordereau.getClient());
        factureAvoir.setStatut(false);
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now);
        factureAvoir.setDate(date);
        factureAvoir.setBordereau(bordereau);
        Map<Integer, Integer> tarifMap = new HashMap<>();

        for (Booking booking : bookings) {
            Integer tarif = booking.getTarif().getId();
            if (tarifMap.containsKey(tarif)) {
                int value = tarifMap.get(tarif);
                tarifMap.put(tarif, value + 1);
            } else {
                tarifMap.putIfAbsent(tarif, 1);
            }
        }
        return tarifMap;


    }

}