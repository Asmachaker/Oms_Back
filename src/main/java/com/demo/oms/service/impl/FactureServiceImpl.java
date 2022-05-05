package com.demo.oms.service.impl;


import com.demo.oms.entity.Facture;
import com.demo.oms.repository.FactureRepository;
import com.demo.oms.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    FactureRepository factureRepository;

    @Override
    public void addFacture(Facture facture) {
        factureRepository.save(facture);
    }

    @Override
    public List<Facture> getAllFacture() {
        return factureRepository.findAll();
    }

    @Override
    public Facture getFacture(Long id) {

        return factureRepository.findById(id).get();
    }

    @Override
    public void EnableFacture(Long id) {
        factureRepository.enableFacture(id);
    }

    public void GenerateFacture()
    {

    }

}