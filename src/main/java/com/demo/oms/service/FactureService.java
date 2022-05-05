package com.demo.oms.service;

import com.demo.oms.entity.Facture;

import java.util.List;

public interface FactureService {
    void addFacture(Facture facture);

    List<Facture> getAllFacture();

    Facture getFacture(Long id);

    void EnableFacture(Long id);
}
