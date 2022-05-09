package com.demo.oms.service;

import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Facture;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface FactureService {
    void addFacture(Facture facture);

    List<Facture> getAllFacture();

    Facture getFacture(Long id);

    void EnableFacture(Long id);

    String exportReport(Bordereau bordereau) throws FileNotFoundException, JRException;
}
