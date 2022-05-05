package com.demo.oms.service;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.entity.Tarif;

import java.util.List;
import java.util.Map;

public interface FactureAvoirService {
    void addFactureAvoir(FactureAvoir factureAvoir);

    FactureAvoir getFactureAvoir(Long id);

    List<FactureAvoir> getAllFactureAvoir();

    void EnableFactureAvoir(Long id);

    Map<Integer,Integer> generateFactureAvoir(List<Booking> bookings, Long id);
}
