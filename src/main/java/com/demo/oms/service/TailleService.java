package com.demo.oms.service;

import com.demo.oms.entity.Taille;

import java.util.List;

public interface TailleService {
    void addTaille(Taille taille);

    List<Taille> getAllTaille();

    Taille getTaille(String id);

    void UpdateTaille(Taille taille);

    void deleteTaille(String id);
}
