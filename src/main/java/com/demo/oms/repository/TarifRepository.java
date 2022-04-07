package com.demo.oms.repository;


import com.demo.oms.entity.Tarif;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TarifRepository extends JpaRepository<Tarif, Integer> {

    @Transactional
    @Query(value= "SELECT * FROM Tarif WHERE MATCH (id,price, shift,taille,zone) AGAINST (?1)", nativeQuery= true)
    List<Tarif> search (String keyboard);


}
