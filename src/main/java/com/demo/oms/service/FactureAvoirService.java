package com.demo.oms.service;

import com.demo.oms.dto.FactureDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.entity.Tarif;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface FactureAvoirService {
    void addFactureAvoir(FactureAvoir factureAvoir);

    FactureAvoir getFactureAvoir(Long id);

    List<FactureAvoir> getAllFactureAvoir();

    void EnableFactureAvoir(Long id);

    Map<Float,Integer> generateFactureAvoir(List<Booking> bookings, Long id);


    List<FactureDTO> generateList(Map<Float, Integer> tarifMap);

    String exportReport(List<Booking> bookings, Long id) throws FileNotFoundException, JRException;
}
