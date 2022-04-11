package com.demo.oms.repository;


import com.demo.oms.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TarifRepository extends JpaRepository<Tarif, Integer> {
    @Transactional
    @Query("SELECT u FROM Tarif u WHERE u.zone = :zone AND u.taille = :taille and u.shift= :shift")
    Tarif getTarifByAtt(
            @Param("zone") Zone zone,
            @Param("taille") Taille taille,
            @Param("shift") Shift shift);

}
