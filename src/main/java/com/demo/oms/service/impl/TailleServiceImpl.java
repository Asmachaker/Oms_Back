package com.demo.oms.service.impl;

import com.demo.oms.entity.Taille;
import com.demo.oms.repository.TailleRepository;
import com.demo.oms.service.TailleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TailleServiceImpl implements TailleService {

    @Autowired
    private TailleRepository tailleRepository;

    @Override
    public void addTaille(Taille taille) {
        tailleRepository.save(taille);
    }

    @Override
    public List<Taille> getAllTaille() {
        return tailleRepository.findAll();
    }


    @Override
    public Taille getTaille(String id) {

        return tailleRepository.findById(id).get();
    }

    @Override
    public void UpdateTaille(Taille taille) {

        tailleRepository.save(taille);

    }

    @Override
    public void deleteTaille(String id) {

        tailleRepository.deleteById(id);
    }
}

