package com.demo.oms.service;

import com.demo.oms.dto.TarifDTO;
import com.demo.oms.entity.Tarif;

import java.util.List;

public interface TarifService {
    void addTarif(Tarif tarif);

    List<Tarif> getAllTarif();

    Tarif getTarif(Integer id);

    void UpdateTarif(TarifDTO tarif);


    Tarif getTarifByAtt(int idZ, String idT, String idS);

    void deleteTarif(Integer id);
}
