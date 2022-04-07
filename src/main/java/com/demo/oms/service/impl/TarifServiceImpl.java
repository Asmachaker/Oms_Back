package com.demo.oms.service.impl;

import com.demo.oms.entity.Tarif;
import com.demo.oms.repository.TarifRepository;
import com.demo.oms.service.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifServiceImpl implements TarifService {

    @Autowired
    private TarifRepository tarifRepository;

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
    public void UpdateTarif(Tarif tarif) {

        tarifRepository.save(tarif);

    }

    @Override
    public List<Tarif> search(String key)
    {return tarifRepository.search(key);}

    @Override
    public void deleteTarif(Integer id) {

         tarifRepository.deleteById(id);
    }
}
